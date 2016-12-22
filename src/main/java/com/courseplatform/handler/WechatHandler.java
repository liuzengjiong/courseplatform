package com.courseplatform.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author chen cy
 * Created by ye on 2016/12/15.
 */
@Controller
@RequestMapping("/wechat")
public class WechatHandler {

    @RequestMapping("/login")
    public String login(HttpServletResponse response) {
        // 获取二维码
        String uuid = "";
        return "";
    }

}
