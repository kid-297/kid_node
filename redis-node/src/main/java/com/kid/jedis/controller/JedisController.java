package com.kid.jedis.controller;

import com.kid.jedis.service.RedisStringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JedisController {
    @Autowired
    private RedisStringService redisStringService;

    @ResponseBody
    @RequestMapping(value = "name")
    public boolean name(String name) {
        boolean flag = false;
        try {
            flag = redisStringService.set("name",name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
