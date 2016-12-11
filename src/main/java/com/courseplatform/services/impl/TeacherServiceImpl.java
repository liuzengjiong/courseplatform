package com.courseplatform.services.impl;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.Courseware;
import com.courseplatform.bean.TeacherCourse;
import com.courseplatform.bean.User;
import com.courseplatform.dao.CourseMapper;
import com.courseplatform.dao.CoursewareMapper;
import com.courseplatform.dao.TeacherCourseMapper;
import com.courseplatform.dao.UserMapper;
import com.courseplatform.handler.TeacherHandler;
import com.courseplatform.services.TeacherService;
import com.courseplatform.util.IDFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherHandler.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursewareMapper coursewareMapper;

    /**
     * TODO 修改数据库表 course的courseid为字符串类型 使用时间戳作为主键标识
     * 添加新课程
     *
     * @param account
     * @param course
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 17:59
     */
    @Override
    @Transactional
    public boolean addCourse(String account, Course course, String[] filepaths) {
        User user = userMapper.selectByPrimaryKey(account);

        // 设置主键
        course.setCourseId(IDFactory.newID());
        // 设置时间
        course.setTime(new Date());
        int i = courseMapper.insert(course);

        // 设置该老师的课程
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourseId(course.getCourseId());
        teacherCourse.setTeacherAccount(account);
        int j = teacherCourseMapper.insert(teacherCourse);

        // 设置老师的课件
        List<Courseware> coursewares = new ArrayList<>();
        for (String filepath : filepaths) {
            Courseware courseware = new Courseware(IDFactory.newID(), teacherCourse.getCourseId(), teacherCourse.getTeacherAccount(), filepath);
            coursewares.add(courseware);
        }
        int k = coursewareMapper.insertCoursewares(coursewares);

        return i + j + k == 2 + filepaths.length;
    }


    /**
     * 获取教师信息
     *
     * @param account
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 17:58
     */
    @Override
    @Transactional
    public User getTeacherInfo(String account) {
        return userMapper.selectByPrimaryKey(account);
    }

    /**
     * 获取教师的所有课程
     *
     * @param account
     *         教师账号
     * @return 课程列表
     * @author ye [15622797401@163.com]
     * @date 2016/12/11 0:36
     */
    @Override
    @Transactional
    public List<Course> getCourseList(String account) {
        return teacherCourseMapper.selectByTeacherAccount(account);
    }

    /**
     * 获取教师的课程id为courseId的课程的所有课件文件列表
     *
     * @param courseId
     *         课程id
     * @param account
     *         教师账号
     * @return 课件列表
     * @author ye [15622797401@163.com]
     * @date 2016/12/11 0:37
     */
    @Override
    @Transactional
    public List<Courseware> getCourseware(String courseId, String account) {
        return coursewareMapper.selectByCourseIdAndTeacherAccount(courseId, account);
    }

    /**
     * 添加课件文件
     *
     * @param account
     * @param courseId
     * @param filepaths
     */
    @Override
    @Transactional
    public void addCoursewares(String account, String courseId, String[] filepaths) {
        List<Courseware> coursewares = new ArrayList<>();
        for (String filepath : filepaths) {
            Courseware courseware = new Courseware(IDFactory.newID(), courseId, account, filepath);
        }
    }
}
