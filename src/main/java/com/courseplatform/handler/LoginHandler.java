package com.courseplatform.handler;

import com.alibaba.fastjson.JSONObject;
import com.courseplatform.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ye
 * @dete 2016年12月6日1:36:51
 */
@Controller
public class LoginHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LoginHandler.class);

    @Autowired
    private LoginService loginService;

    /**
     * 登录,需要账号,密码,类型
     *
     * @param response
     * @param account
     *         账号
     * @param password
     *         密码
     * @param type
     *         类型
     * @return
     * @author ye
     * @dete 2016年12月8日21:23:24
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletResponse response, String account, String password, int type) {
        String md5 = loginService.login(account, password, type);
        JSONObject jsonObject = new JSONObject();
        if (null != md5 && md5.length() > 0) {
            response.addCookie(new Cookie("userCode", md5));
            response.addCookie(new Cookie("account", account));
            jsonObject.put("code", "1");
        } else {
            jsonObject.put("code", "0");
        }
        LOG.info("Login:" + jsonObject.toString());
        return jsonObject.toString();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        LOG.info("***************************************************");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ll", "Hello");
        return jsonObject.toString();
    }

}