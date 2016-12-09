package com.courseplatform.services;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.TeacherCourse;
import com.courseplatform.bean.User;
import com.courseplatform.dao.CourseMapper;
import com.courseplatform.dao.TeacherCourseMapper;
import com.courseplatform.dao.UserMapper;
import com.courseplatform.handler.TeacherHandler;
import com.courseplatform.util.IDFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
@Service
public class TeacherService {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherHandler.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;
    @Autowired
    private CourseMapper courseMapper;

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
    @Transactional
    public boolean addCourse(String account, Course course, TeacherCourse teacherCourse) {
        User user = userMapper.selectByPrimaryKey(account);
        // 设置主键
        course.setCourseId(IDFactory.newID());
        // 设置时间
        course.setTime(new Date());
        int i = courseMapper.insert(course);
        // 设置课程
        teacherCourse.setCourseId(course.getCourseId());
        teacherCourse.setTeacherAccount(account);
        int j = teacherCourseMapper.insert(teacherCourse);
        return i + j == 2;
    }

    /**
     * 获取教师信息
     *
     * @param account
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 17:58
     */
    @Transactional
    public User getTeacherInfo(String account) {
        return userMapper.selectByPrimaryKey(account);
    }

}
