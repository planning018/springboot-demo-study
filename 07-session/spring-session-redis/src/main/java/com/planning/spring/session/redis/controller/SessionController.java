package com.planning.spring.session.redis.controller;

import com.planning.spring.session.redis.vo.RequestBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yxc
 * @since 2020-05-27 21:03
 **/
@RestController
@RequestMapping("/session")
public class SessionController {

    @PostMapping("/set")
    public void setSession(HttpSession session, @RequestBody RequestBean requestBean) {
        // 暂不做 非空校验
        session.setAttribute(requestBean.getKey(), requestBean.getValue());
    }

    @GetMapping("/getAll")
    public Map<String, Object> getAll(HttpSession session) {
        Map<String, Object> result = new HashMap<>(16);
        // 遍历
        for (Enumeration<String> attributeNames = session.getAttributeNames();
             attributeNames.hasMoreElements();) {
            String key = attributeNames.nextElement();
            Object value = session.getAttribute(key);
            result.put(key, value);
        }
        // 返回
        return result;
    }
}