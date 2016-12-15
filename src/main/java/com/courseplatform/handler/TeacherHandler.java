package com.courseplatform.handler;

import com.alibaba.fastjson.JSONObject;
import com.courseplatform.bean.Course;
import com.courseplatform.bean.Courseware;
import com.courseplatform.bean.ResponseCode;
import com.courseplatform.bean.User;
import com.courseplatform.services.CoursewareService;
import com.courseplatform.services.TeacherService;
import com.courseplatform.util.FileUtil;
import com.courseplatform.util.IDFactory;
import com.courseplatform.util.MD5Util;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private CoursewareService coursewareService;

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
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String info() {
        LOG.info("TeacherHandler--info--return:" + JSONObject.toJSONString(user));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            jsonObject.put("code", "1");
            jsonObject.put("user", user);
        }
        return jsonObject.toString();
    }

    /**
     * 添加课程
     *
     * @param files
     *         课程课件
     * @param course
     *         课程信息
     * @return 添加课程是否成功
     * @author ye [15622797401@163.com]
     * @date 2016/12/10 23:03
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ApiOperation(value = "添加课程", response = ResponseCode.class, notes = "老师添加课程", produces = "prodeuces是什么")
    public String addCourse(@ApiParam("课件文件") @RequestParam(value = "files", required = false) MultipartFile[] files,
                            @ApiParam("课程信息") Course course) {
        String courseJson = JSONObject.toJSONString(course);
        LOG.info("addCourse:" + course.toString());
//        for (int i = 0; i < files.length; i++) {
//            String filename = files[i].getOriginalFilename();
//            LOG.info("第" + i + "个文件名:" + filename);
//        }
        JSONObject jsonObject = new JSONObject();
        try {
            // 上传课件文件
            course.setCourseId(IDFactory.newID());
            // 设置时间
            course.setTime(new Date());
            teacherService.addCourse(user.getAccount(), course, uploadFiles(files, course.getCourseId()));
            jsonObject.put("code", "1");
        } catch (IOException e) {
            // 上传失败
            jsonObject.put("code", "0");
        }
        LOG.info("return :");
        return "redirect:/subHtml/teacherIndex.html";
    }

    /**
     * 删除课程
     *
     * @param courseId
     *         课程ID
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 13:02
     */
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCourse(@RequestParam(value = "courseId") String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            LOG.info("delete:[courseId:" + courseId + ",account:" + user.getAccount() + "]");
            int i = teacherService.deleteCourse(courseId, user.getAccount());
            LOG.info("i:" + i);
            if (i == 1) {
                jsonObject.put("code", "1");
            }
        }
        LOG.info(jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 查询某一课程的详细信息
     *
     * @param courseId
     *         课程的id
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 13:10
     */
    @RequestMapping(value = "/getCourse", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取课程详细信息", response = Course.class, nickname = "nickname")
    public String getCourse(String courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            Course course = teacherService.getCourse(courseId, user.getAccount());
            List<Courseware> coursewares = coursewareService.getCourseware(courseId, user.getAccount());
            if (null != course && null != coursewares) {
                jsonObject.put("code", "1");
                jsonObject.put("course", course);
                jsonObject.put("coursewares", coursewares);
            }
        }
        return jsonObject.toString();
    }

    /**
     * 获取教师的课程列表
     *
     * @return 课程列表
     */
    @RequestMapping(value = "/getCourses", method = RequestMethod.GET)
    @ResponseBody
    public String getCourses() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        if (null != user) {
            LOG.info("getCourses-user:" + user.getAccount());
            List<Course> courses = teacherService.getCourseList(user.getAccount());
            jsonObject.put("code", "1");
            jsonObject.put("size", courses.size());
            jsonObject.put("list", courses);
            jsonObject.put("username", user.getName());
            LOG.info(jsonObject.toString());
        }
        return jsonObject.toJSONString();
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
    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    @ResponseBody
    public String uploadAddCourseware(@RequestParam MultipartFile[] files, String courseId) {
        JSONObject jsonObject = new JSONObject();
        try {
            coursewareService.addCoursewares(user.getAccount(), courseId, uploadFiles(files, courseId));
            jsonObject.put("code", "1");
        } catch (IOException e) {
            jsonObject.put("code", "0");
        }
        return jsonObject.toString();
    }

    /**
     * 获取课程的所有课件
     *
     * @param courseId
     *         课程ID
     * @return json
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 12:36
     */
    @RequestMapping(value = "/getCourseware", method = RequestMethod.GET)
    @ResponseBody
    public String getCoursewaresByCourse(String courseId) {
        JSONObject jsonObject = new JSONObject();
        if (null != user) {
            List<Courseware> coursewares = coursewareService.getCourseware(courseId, user.getAccount());
            jsonObject.put("code", "1");
            jsonObject.put("size", coursewares.size());
            jsonObject.put("list", coursewares);
        } else {
            jsonObject.put("code", "0");
        }
        return jsonObject.toString();
    }

    /**
     * 删除课件
     *
     * @param cousewareId
     *         课件ID
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 12:45
     */
    @RequestMapping(value = "/deleteCourseware", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCourseware(String cousewareId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        if (null != user) {
            int i = coursewareService.deleteCourseware(cousewareId);
            if (i == 1) {
                jsonObject.put("code", "1");
            }
        }
        return jsonObject.toString();
    }

    /**
     * 上传课件文件，设置好路径
     *
     * @param files
     * @param courseId
     * @return
     * @throws IOException
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 20:09
     */
    private String[] uploadFiles(MultipartFile[] files, String courseId) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/uploadFile");
        path += "/" + user.getAccount() + "/" + courseId;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        LOG.info("uploadFiles--" + path);
        return FileUtil.uploadFile(files, path);
    }

}