package com.zb.sms.common.cache;

import com.zb.sms.common.redis.StringRedisComponent;
import com.zb.sms.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能: 缓存管理公共基类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 17:55
 * 版本: V1.0
 */
public abstract class BaseCacheManager {

    /**
     * 日志器
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * redis字符操作对象
     */
    @Autowired(required = false)
    protected StringRedisComponent stringRedisComponent;

    /**
     * 获取redis中的缓存版本
     *
     * @param redisCacheVerKey
     * @return
     */
    public String getRedisCacheVersion(String redisCacheVerKey) {
        String redisCacheVer = null;
        try {
            redisCacheVer = stringRedisComponent.get(redisCacheVerKey);
            logger.info(redisCacheVerKey + ":" + redisCacheVer);
        } catch (Exception e) {
            logger.error("获取redis数据失败", e);
        }
        return redisCacheVer;
    }

    /**
     * 判断是否需要更新本地缓存
     *
     * @param memoryCacheVer
     * @param redisCacheVer
     * @return
     */
    public boolean isNeedUpdateCache(String memoryCacheVer, String redisCacheVer) {
        if (StringUtils.isBlank(memoryCacheVer)) {
            //如果本地缓存版本为空,则需要更新缓存
            return true;
        } else {
            if (StringUtils.isBlank(redisCacheVer)) {
                //如果redis缓存版本为空,则不需要更新缓存
                return false;
            } else {
                //如果版本一直则不更新
                return (!memoryCacheVer.equals(redisCacheVer));
            }
        }
    }

    /**
     * 生成内存缓存版本
     *
     * @return
     */
    public String generateMemoryCacheVersion(String redisCacheVer) {
        if (StringUtils.isNotBlank(redisCacheVer)) {
            //与redis版本同步
            return redisCacheVer;
        } else {
            //生成内存缓存版本号
            return DateUtils.format(DateUtils.now(), DateUtils.DEFAULT_FULL_TIMESTAMP_FORMAT);
        }
    }

    /**
     * 加载内存缓存
     */
    public abstract void loadMemoryCache();
}
