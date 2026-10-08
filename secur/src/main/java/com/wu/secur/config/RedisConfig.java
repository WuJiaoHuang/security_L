package com.wu.secur.config;

import com.wu.secur.utils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory connectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        // 设置 Redis 连接工厂
        template.setConnectionFactory(connectionFactory);

        // value 使用 FastJson 序列化
        FastJsonRedisSerializer serializer =
                new FastJsonRedisSerializer(Object.class);

        // key 使用 String 序列化
        template.setKeySerializer(new StringRedisSerializer());

        // value 使用 FastJson 序列化
        template.setValueSerializer(serializer);

        // hash 的 key 使用 String 序列化
        template.setHashKeySerializer(new StringRedisSerializer());

        // hash 的 value 使用 FastJson 序列化
        template.setHashValueSerializer(serializer);

        // 初始化 RedisTemplate
        template.afterPropertiesSet();

        return template;
    }
}