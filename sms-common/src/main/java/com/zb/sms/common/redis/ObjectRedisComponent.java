package com.zb.sms.common.redis;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 功能: Redis对象操作组件
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 14:29
 * 版本: V1.0
 */
public class ObjectRedisComponent {

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 保存数据/更新数据 并设置超时时间<br/>
     * 如果value没有实现Serializable接口,则会保存不成功(内部已经处理了异常)
     *
     * @param key     传入相同的key会覆盖之前同名key的数据的内容及设置
     * @param value   缓存的值，object类型
     * @param timeout 超时时间，单位为秒，如果传值<=0，则为永不过期
     */
    public void saveOrUpdate(String key, Object value, long timeout) {
        if (timeout <= 0) {
            set(key, value);
        } else {
            setEx(key, value, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 保存数据/更新数据 并设置超时时间<br/>
     * 如果value没有实现Serializable接口,则会保存不成功(内部已经处理了异常)
     *
     * @param key   传入相同的key会覆盖之前同名key的数据的内容及设置
     * @param value 缓存的值，object类型
     */
    public void set(String key, Object value) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, value);
    }

    /**
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    public void setEx(String key, Object value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, value, timeout, timeUnit);
    }

    /**
     * 根据key查询得到对应的值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        return valueOper.get(key);
    }

    /**
     * 检查key和对应的值在缓存中是否存在
     *
     * @param key
     * @return 存在返回 true，否则返回false
     */
    public boolean exist(String key) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        boolean exist = valueOper.getOperations().hasKey(key);
        return exist;
    }

    /**
     * 根据key删除key及对应的值
     *
     * @param id
     */
    public void delete(String id) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        RedisOperations<String, Object> redisOperations = valueOper.getOperations();
        redisOperations.delete(id);
    }

    /**
     * 自增序列号
     *
     * @param key key
     * @return 返回唯一值
     */
    public long incr(String key) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        return valueOper.increment(key, 1);
    }

    /**
     * 列举keys
     *
     * @param pattern 匹配符
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
