package com.hhplus.commerce.app.common.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * create on 5/1/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")
  private String host;

  @Value("${spring.data.redis.port}")
  private int port;

  private static final String REDISSON_HOST_PREFIX = "redis://";

  @Bean(name = "redissonClient")
  public RedissonClient redissonClient(){
    Config config = new Config();
    config.useSingleServer().setAddress(REDISSON_HOST_PREFIX + host + ":" + port);

    return Redisson.create(config);
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(
        new RedisStandaloneConfiguration(host, port)
    );
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setDefaultSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper());

    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
            .entryTtl(Duration.ofMinutes(3L)); // 캐쉬 저장 시간 3분 설정

    return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .build();
  }

  private ObjectMapper objectMapper() {
    PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
            .builder()
            .allowIfSubType(Object.class)
            .build();
    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.registerModule(new JavaTimeModule());
    mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
    return mapper;
  }

}
