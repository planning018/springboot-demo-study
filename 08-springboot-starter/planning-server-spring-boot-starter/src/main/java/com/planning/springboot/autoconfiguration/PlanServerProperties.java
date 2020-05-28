package com.planning.springboot.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yxc
 * @since 2020-05-28 19:38
 **/
@ConfigurationProperties(prefix = "planning.server")
public class PlanServerProperties {

    /**
     * 默认端口
     */
    private static final Integer DEFAULT_PORT = 8000;

    /**
     * 实际端口
     */
    private Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort() {
        return port;
    }

    public PlanServerProperties setPort(Integer port) {
        this.port = port;
        return this;
    }
}