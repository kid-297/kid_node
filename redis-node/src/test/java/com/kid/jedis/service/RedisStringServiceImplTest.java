package com.kid.jedis.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.yml"})
@Slf4j
public class RedisStringServiceImplTest {

    @Autowired
    private RedisStringService redisStringService;

    @Test
    public void set() {
        boolean rs = false;
        try {
            rs = redisStringService.set("username","1111");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void set1() {
    }

    @Test
    public void del() {
    }

    @Test
    public void get() {
    }

    @Test
    public void append() {
    }

    @Test
    public void setbit() {
    }

    @Test
    public void bitcount() {
    }
}
