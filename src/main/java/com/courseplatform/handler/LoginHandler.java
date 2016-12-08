package com.courseplatform.handler;

import com.courseplatform.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ye
 * @dete 2016年12月6日1:36:51
 */
@Controller
public class LoginHandler {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login.do")
    public String login(HttpServletResponse response, String account, String password, int type) {
        String md5 = loginService.login(account, password, type);
        if (null != md5 && md5.length() > 0) {
            response.addCookie(new Cookie("userCode", md5));
            return "";
        } else {
            return "";
        }
    }
}