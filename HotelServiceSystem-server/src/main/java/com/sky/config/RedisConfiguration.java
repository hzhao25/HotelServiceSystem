package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Resource
    private RedisConnectionFactory connectionFactory;

    //RedisTemplate 是 Spring Data Redis 提供的一个核心类，用于简化与 Redis 数据库的交互操作
    @Bean
    public RedisTemplate redisTemplate(){
        log.info("开始创建Reids模板对象……");
        RedisTemplate redisTemplate=new RedisTemplate();
        //设置reids的连接工厂对象,创建与数据库的连接
        redisTemplate.setConnectionFactory(connectionFactory);
        //设置redis key的序列化器（将 Java 对象转换为字节数组，以便存储到 Redis 数据库中）
        //StringRedisSerializer 用于将字符串类型的 key 进行序列化和反序列化，确保在 Redis 中存储的 key 是字符串类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器为StringRedisSerializer，确保序列化和反序列化一致
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
