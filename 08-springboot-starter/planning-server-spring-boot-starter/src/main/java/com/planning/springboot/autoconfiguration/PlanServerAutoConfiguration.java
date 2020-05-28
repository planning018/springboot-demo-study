package com.planning.springboot.autoconfiguration;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 自动配置类
 * <p>
 * // 声明配置类 @Configuration
 * // 使 PlanServerProperties 配置属性类生效 @EnableConfigurationProperties
 *
 * @author yxc
 * @since 2020-05-28 19:37
 **/
@Configuration
@EnableConfigurationProperties(PlanServerProperties.class)
public class PlanServerAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(PlanServerAutoConfiguration.class);

    /**
     * // @Bean 声明创建 Bean
     * // @ConditionalOnClass(HttpServer.class)
     *      需要项目中存在 com.sun.net.httpserver.HttpServer 类，该类为 JDK 自带，所以一定成立。
     * @param serverProperties
     * @return
     * @throws IOException
     */
    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(PlanServerProperties serverProperties) throws IOException {
        // 创建 HttpServer 对象，并启动
        HttpServer server = HttpServer.create(new InetSocketAddress(serverProperties.getPort()), 0);
        server.start();
        logger.info("[httpServer][启动服务器成功：端口为：{}]", serverProperties.getPort());

        // 返回
        return server;
    }
}