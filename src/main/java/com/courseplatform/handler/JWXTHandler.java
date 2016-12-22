package com.courseplatform.handler;

import com.alibaba.fastjson.JSONObject;
import com.courseplatform.services.impl.JWXTSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author chen cy
 * Created by ye on 2016/12/17.
 */
@Controller
@RequestMapping("/jwxt")
public class JWXTHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JWXTHandler.class);

    @RequestMapping(value = "/confirmTeach", method = RequestMethod.GET)
    @ResponseBody
    public String confirmTeach(String number, String passwd, HttpServletResponse response) {
        LOG.info("comfirmTeach-data:[number:" + number + ",passwd:" + passwd + "]");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        for (int i = 0; i < 10; i++) {
            String string = JWXTSpider.confirmTeach(number, passwd);
            if (null != string && string.length() > 0) {
                LOG.info("comfirmTeach-return:[name:" + string + "]");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", string);
                return jsonObject.toString();
            }
        }
        return "";
    }

}
