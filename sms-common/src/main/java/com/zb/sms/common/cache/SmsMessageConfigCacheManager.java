package com.zb.sms.common.cache;

import com.zb.sms.common.constants.ServiceStatus;
import com.zb.sms.common.dao.mapper.SmsMessageConfigMapper;
import com.zb.sms.common.model.SmsMessageConfigDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能: 消息路由缓存管理器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/15 0015 11:05
 * 版本: V1.0
 */
@Component
public class SmsMessageConfigCacheManager extends BaseCacheManager {

    /**
     * redis中缓存版本KEY值
     */
    private final static String REDIS_CACHE_VERSION_KEY = "CACHE_VERSION:SMS_MESSAGE_CONFIG_CACHE_VERSION";

    /**
     * 内存缓存版本
     */
    private static String CACHE_VERSION;

    /**
     * 是否正在刷新缓存
     */
    private static boolean IS_LOADING_CACHE = false;

    /**
     * 数据访问
     */
    @Autowired
    private SmsMessageConfigMapper messageConfigMapper;

    /**
     * 内存缓存
     */
    private static ConcurrentHashMap<String, SmsMessageConfigDo> cacheMap = new ConcurrentHashMap<String, SmsMessageConfigDo>();

    /**
     * 加载本地缓存
     */
    @Override
    public void loadMemoryCache() {
        try {
            if (!IS_LOADING_CACHE) {
                //加载redis缓存版本
                String redisCacheVer = this.getRedisCacheVersion(REDIS_CACHE_VERSION_KEY);
                //比较内存缓存版本与redis缓存版本,确定是否需要更新内存缓存
                if (this.isNeedUpdateCache(CACHE_VERSION, redisCacheVer)) {
                    try {
                        //设置正在更新缓存
                        IS_LOADING_CACHE = true;

                        //从数据库中加载缓存内容
                        SmsMessageConfigDo messageConfigDo = new SmsMessageConfigDo();
                        messageConfigDo.setStatus(ServiceStatus.USING.getValue());
                        List<SmsMessageConfigDo> messageConfigDoList = messageConfigMapper.selectList(messageConfigDo);

                        //清空内存缓存
                        cacheMap.clear();
                        if (!CollectionUtils.isEmpty(messageConfigDoList)) {
                            for (SmsMessageConfigDo infoDo : messageConfigDoList) {
                                cacheMap.put(infoDo.getSysId() + "_" + infoDo.getSmsCode(), infoDo);
                            }
                        }

                        //生成内存缓存版本号
                        CACHE_VERSION = this.generateMemoryCacheVersion(redisCacheVer);
                    } catch (Exception ne) {
                        throw ne;
                    } finally {
                        //设置结束更新缓存
                        IS_LOADING_CACHE = false;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("加载SmsMessageConfigCache失败", e);
        }
    }

    /**
     * 从内存缓存中获取缓存对象
     *
     * @param sysId
     * @return
     */
    public SmsMessageConfigDo getMemoryCacheObject(Integer sysId, String smsCode) {
        SmsMessageConfigDo messageConfigDo = null;
        //防止在更新缓存时加载不到数据
        if (IS_LOADING_CACHE && cacheMap.isEmpty()) {
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                logger.error("缓存刷新等待异常", e);
            }
        }
        //获取缓存
        String key = sysId + "_" + smsCode;
        if (cacheMap.containsKey(key)) {
            messageConfigDo = cacheMap.get(key);
        }
        return messageConfigDo;
    }
}
