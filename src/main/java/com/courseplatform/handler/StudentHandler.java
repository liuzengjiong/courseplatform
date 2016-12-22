package com.courseplatform.handler;

import com.alibaba.fastjson.JSONObject;
import com.courseplatform.bean.Course;
import com.courseplatform.bean.Courseware;
import com.courseplatform.bean.User;
import com.courseplatform.services.CoursewareService;
import com.courseplatform.services.StudentService;
import com.courseplatform.services.TeacherService;
import com.courseplatform.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/12.
 */
@RequestMapping("/student")
@Controller
public class StudentHandler {
    private static final Logger LOG = LoggerFactory.getLogger(StudentHandler.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private TeacherService teacherService;

    private User user;

    private HttpServletRequest request;

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
     * 获取学生个人信息
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String info() {
        LOG.info("StudentHandler--info--return:" + JSONObject.toJSONString(user));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            jsonObject.put("code", "1");
            jsonObject.put("user", user);
        }
        return jsonObject.toString();
    }


    /**
     * 获取课程列表
     *
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:58
     */
    @RequestMapping(value = "/getCourses", method = RequestMethod.GET)
    @ResponseBody
    public String getCourses() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            List<Course> courses = studentService.getCourses(user.getAccount());
            jsonObject.put("code", "1");
            jsonObject.put("size", courses.size());
            jsonObject.put("list", courses);
        }
        return jsonObject.toJSONString();
    }


    /**
     * 获取课程详细信息
     *
     * @param courseId
     *         课程ID
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:57
     */
    @RequestMapping(value = "/getCourse", method = RequestMethod.GET)
    @ResponseBody
    public String getCourse(String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        LOG.info("[getCourse] courseId:" + courseId);
        if (null != user) {
            Course course = studentService.getCourseByStudentAndCourseId(courseId, user.getAccount());
            LOG.info(course.toString());
            List<Courseware> coursewares = coursewareService.getCourseware(courseId, course.getTeacherAccount());
            if (null != course) {
                jsonObject.put("code", "1");
                jsonObject.put("coursewares", coursewares);
                jsonObject.put("course", course);
            }
        }
        return jsonObject.toJSONString();
    }

    /**
     * 获取课程的课件信息
     *
     * @param courseId
     *         课程ID
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:57
     */
    @RequestMapping(value = "/getCoursewares", method = RequestMethod.GET)
    @ResponseBody
    public String getCoursewares(String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            List<Courseware> coursewares = coursewareService.getCourseware(courseId, user.getAccount());
            jsonObject.put("code", "1");
            jsonObject.put("size", coursewares.size());
            jsonObject.put("list", coursewares);
        }
        return jsonObject.toJSONString();
    }

}
