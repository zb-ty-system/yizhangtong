package com.zb.sms.common.cache;

import com.zb.sms.common.constants.ServiceStatus;
import com.zb.sms.common.dao.mapper.SmsChannelInfoMapper;
import com.zb.sms.common.model.SmsChannelInfoDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能: 短信渠道缓存管理器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/15 0015 12:30
 * 版本: V1.0
 */
@Component
public class SmsChannelInfoCacheManager extends BaseCacheManager {

    /**
     * redis中缓存版本KEY值
     */
    private final static String REDIS_CACHE_VERSION_KEY = "CACHE_VERSION:SMS_CHANNEL_INFO_CACHE_VERSION";

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
    private SmsChannelInfoMapper channelInfoMapper;

    /**
     * 内存缓存
     */
    private static ConcurrentHashMap<String, SmsChannelInfoDo> cacheMap = new ConcurrentHashMap<String, SmsChannelInfoDo>();

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
                        SmsChannelInfoDo channelInfoDo = new SmsChannelInfoDo();
                        channelInfoDo.setStatus(ServiceStatus.USING.getValue());
                        List<SmsChannelInfoDo> channelInfoDoList = channelInfoMapper.selectList(channelInfoDo);

                        //清空内存缓存
                        cacheMap.clear();
                        if (!CollectionUtils.isEmpty(channelInfoDoList)) {
                            for (SmsChannelInfoDo infoDo : channelInfoDoList) {
                                cacheMap.put(infoDo.getId().toString(), infoDo);
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
            logger.error("加载SmsChannelInfoCache失败", e);
        }
    }

    /**
     * 从内存缓存中获取缓存对象
     *
     * @param id
     * @return
     */
    public SmsChannelInfoDo getMemoryCacheObject(Integer id) {
        SmsChannelInfoDo channelInfoDo = null;
        //防止在更新缓存时加载不到数据
        if (IS_LOADING_CACHE && cacheMap.isEmpty()) {
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                logger.error("缓存刷新等待异常", e);
            }
        }
        //获取缓存
        if (cacheMap.containsKey(id.toString())) {
            channelInfoDo = cacheMap.get(id.toString());
        }
        return channelInfoDo;
    }
}
