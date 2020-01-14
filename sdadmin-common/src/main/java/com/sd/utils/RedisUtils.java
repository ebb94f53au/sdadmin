package com.sd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author siyang
 * @create 2020-01-12 15:01
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${jwt.online-key}")
    private String onlineKey;

    /**
     * 存入数据
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 存入数据,指定多少秒
     * @param key
     * @param value
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    public boolean set(String key,Object value,long time){
        try {
            if(time >0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    /**
     * 存入数据,指定多少时间
     * @param key
     * @param value
     * @param time  时间 time要大于0 如果time小于等于0 将设置无限期
     * @param timeUnit 时间单位
     * @return
     */
    public boolean set(String key,Object value,long time,TimeUnit timeUnit){
        try {
            if(time >0){
                redisTemplate.opsForValue().set(key,value,time,timeUnit);
            }else{
                set(key,value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    /**
     * 取出数据
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null :redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除数据
     * @param key 可以传递多个值
     */
    public void del(String... key){
        if(key!=null && key.length > 0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }

        }

    }



}
