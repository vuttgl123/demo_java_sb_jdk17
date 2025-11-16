package com.demo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test-redis")
    public String testRedis() {
        try {
            redisTemplate.opsForValue().set("test-key", "Hello Redis");
            String value = redisTemplate.opsForValue().get("test-key");
            return "Redis OK, value = " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis ERROR: " + e.getMessage();
        }
    }
}
