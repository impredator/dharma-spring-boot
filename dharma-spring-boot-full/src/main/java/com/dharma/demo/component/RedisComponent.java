package com.dharma.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

    @Autowired
    private StringRedisTemplate template;

    public void set(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        if(!template.hasKey(key)) {
            ops.set(key, value);
        }
    }

    public String get(String key) {
        return template.opsForValue().get(key);
    }

    public void delete(String key) {
        template.delete(key);
    }
}
