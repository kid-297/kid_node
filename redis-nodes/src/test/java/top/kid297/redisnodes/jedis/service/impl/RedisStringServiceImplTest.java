package top.kid297.redisnodes.jedis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.kid297.redisnodes.jedis.service.RedisStringService;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
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
        boolean rs = false;
        try {
            rs = redisStringService.set("username1","2222",222222L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void del() {
        boolean rs = false;
        try {
            rs = redisStringService.del("username1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void get() {
        String rs = null;
        try {
            rs = redisStringService.get("username1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void append() {
        boolean rs = false;
        try {
            rs = redisStringService.append("username1","5021111");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void setbit() {
        boolean rs = false;
        try {
            rs = redisStringService.setbit("bittest",1111,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void getbit(){
        boolean rs = false;
        try {
            rs = redisStringService.getbit("bittest",1111);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }

    @Test
    public void bitcount() {
        Long rs = null;
        try {
            rs = redisStringService.bitcount("bittest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:{}",rs);
    }
}