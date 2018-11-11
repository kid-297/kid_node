package com.kid.jedis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface RedisStringService {

    /**
     * 添加key value
     * @param key  键
     * @param value 值
     * @return true/false
     */
    boolean set(String key,String value) throws Exception;

    /**
     * 添加key value 并且设置存活时间
     * @param key
     * @param value
     * @param activeTime 秒
     * @return true/false
     */
    boolean set(String key,String value,long activeTime)throws Exception;

    /**
     * 删除
     * @param key
     * @return
     */
    boolean del(String key) throws Exception;

    /**
     * 获取参数
     * @param key
     * @return
     * @throws Exception
     */
    String get(String key) throws Exception;

}
