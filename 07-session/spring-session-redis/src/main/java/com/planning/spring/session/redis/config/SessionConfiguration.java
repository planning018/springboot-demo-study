package com.planning.spring.session.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Session 配置管理
 * 自动化配置 Spring Session 使用 Redis 作为数据源 | @EnableRedisHttpSession
 *
 * @author yxc
 * @since 2020-05-27 20:51
 **/
@Configuration
@EnableRedisHttpSession
public class SessionConfiguration {

    /**
     * 创建 {@link org.springframework.session.data.redis.RedisOperationsSessionRepository}
     * 使用的 RedisSerializer Bean.
     *
     * @return RedisSerializer Bean
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }
}