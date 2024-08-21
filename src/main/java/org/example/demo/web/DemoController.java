package org.example.demo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DemoController {

    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/redis/increment")
    public String redis() {
        stringRedisTemplate.opsForValue().increment("key", 1);
        return stringRedisTemplate.opsForValue().get("key");
    }

    @GetMapping("/redis/get")
    public String redis2() {
        return stringRedisTemplate.opsForValue().get("key");
    }

}
