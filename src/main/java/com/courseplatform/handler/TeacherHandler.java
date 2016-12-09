package com.courseplatform.handler;

import com.alibaba.fastjson.JSONObject;
import com.courseplatform.bean.User;
import com.courseplatform.services.TeacherService;
import com.courseplatform.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
@RequestMapping("/teacher")
@Controller
public class TeacherHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherHandler.class);
    public User user;
    @Autowired
    private TeacherService teacherService;

    /**
     * 用于验证用户是否已经登录 使用cookie进行验证
     *
     * @param usercode
     *         用户码
     * @param account
     *         账号
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 21:11
     */
    @ModelAttribute
    public void getUser(@CookieValue("userCode") String usercode, @CookieValue("account") String account) {
        LOG.info("TeacherHandler-getUser-data:{userCode:" + usercode + ",account:" + account + "}");
        user = teacherService.getTeacherInfo(account);
        if (null != user) {
            // 获取 MD5
            String md5 = MD5Util.getMD5(user);
            if (!usercode.equals(md5)) {
                user = null;
            }
        }
    }

    /**
     * 获取用户信息
     *
     * @return 返回用户信息的json字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 19:44
     */
    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        LOG.info("TeacherHandler--info--return:" + JSONObject.toJSONString(user));
        return JSONObject.toJSONString(user);
    }

    public String addCourse(@RequestParam(value = "file", required = false) MultipartFile[] file){
        return "";
    }

    // TODO 上传课件 可能为多个文件 新建课程时上传文件 返回文件路径
    public String uploadCourseware() {
        return "";
    }

    // TODO 上传课件 往已经存在的课程中增加课件
    public String uploadAddCourseware() {
        return "";
    }

    // TODO 删除课件
    public String deleteCourseware() {
        return "";
    }

}
