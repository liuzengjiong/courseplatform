package com.courseplatform.services.impl;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.User;
import com.courseplatform.dao.CourseMapper;
import com.courseplatform.dao.StudentCourseMapper;
import com.courseplatform.dao.UserMapper;
import com.courseplatform.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/11.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public User info(String account) {
        return userMapper.selectByPrimaryKey(account);
    }

    @Override
    public Course getCourse(String courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    public Course getCourseByStudentAndCourseId(String courseId, String studentAccount) {
        return studentCourseMapper.selectCourseByStudentAndCourseId(courseId, studentAccount);
    }

    @Override
    public List<Course> getCourses(String studentAccount) {
        return studentCourseMapper.selectCoursesByStudent(studentAccount);
    }

}
