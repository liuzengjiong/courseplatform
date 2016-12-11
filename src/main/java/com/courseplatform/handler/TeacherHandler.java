package com.courseplatform.handler;

import com.alibaba.fastjson.JSONObject;
import com.courseplatform.bean.Course;
import com.courseplatform.bean.User;
import com.courseplatform.services.TeacherService;
import com.courseplatform.util.FileUtil;
import com.courseplatform.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
@RequestMapping("/teacher")
@Controller
public class TeacherHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherHandler.class);
    public User user;
    private HttpServletRequest request;

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
    public void getUser(@CookieValue("userCode") String usercode, @CookieValue("account") String account, HttpServletRequest request) {
        LOG.info("TeacherHandler-getUser-data:{userCode:" + usercode + ",account:" + account + "}");
        this.request = request;
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

    /**
     * 添加课程
     *
     * @param files
     *         课程课件
     * @param course
     *         课程信息
     * @param request
     *         请求
     * @return 添加课程是否成功
     * @author ye [15622797401@163.com]
     * @date 2016/12/10 23:03
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public String addCourse(@RequestParam(value = "files", required = false) MultipartFile[] files, Course course, HttpServletRequest request) {
        String courseJson = JSONObject.toJSONString(course);
        LOG.info("addCourse:" + course.toString());
//        for (int i = 0; i < files.length; i++) {
//            String filename = files[i].getOriginalFilename();
//            LOG.info("第" + i + "个文件名:" + filename);
//        }
        JSONObject jsonObject = new JSONObject();
        try {
            // 上传课件文件
            teacherService.addCourse(user.getAccount(), course, FileUtil.uploadFile(files, request));
            jsonObject.put("code", "1");
        } catch (IOException e) {
            // 上传失败
            jsonObject.put("code", "0");
        }
        return jsonObject.toString();
    }

    /**
     * 上传课件 往已经存在的课程中增加课件
     *
     * @param files
     *         课件文件
     * @param courseId
     *         课程编号
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/11 15:00
     */
    public String uploadAddCourseware(@RequestParam MultipartFile[] files, String courseId) {
        JSONObject jsonObject = new JSONObject();
        try {
            teacherService.addCoursewares(user.getAccount(), courseId, FileUtil.uploadFile(files, request));
            jsonObject.put("code", "1");
        } catch (IOException e) {
            jsonObject.put("code", "0");
        }
        return jsonObject.toString();
    }

    // TODO 获取课程的所有课件文件
    public String getCoursewaresByCourse(String courseId) {
        return "";
    }

    // TODO 删除课件
    public String deleteCourseware(String cousewareId) {

        return "";
    }
}
