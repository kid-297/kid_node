package top.kid297.redisnodes.jedis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kid297.redisnodes.jedis.service.RedisStringService;

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
