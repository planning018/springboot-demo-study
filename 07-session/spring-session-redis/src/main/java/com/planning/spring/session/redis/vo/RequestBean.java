package com.planning.spring.session.redis.vo;

import lombok.Data;

/**
 * Session set 测试对象
 *
 * @author yxc
 * @since 2020-05-27 21:05
 **/
@Data
public class RequestBean {

    private String key;

    private String value;
}