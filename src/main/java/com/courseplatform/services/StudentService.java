package com.courseplatform.services;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/11.
 */
public interface StudentService {

    /**
     * 获取学生的信息
     *
     * @param account
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:21
     */
    @Transactional
    User info(String account);

    /**
     * 获取课程详细信息
     *
     * @param courseId
     *         课程ID
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:21
     */
    @Transactional
    Course getCourse(String courseId);

    /**
     * 获取学生的所有课程
     *
     * @param studentAccount
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:21
     */
    @Transactional
    List<Course> getCourses(String studentAccount);

    /**
     *
     * @param courseId
     * @param studentAccount
     * @return
     */
    Course getCourseByStudentAndCourseId(@Param("courseId") String courseId, @Param("studentAccount") String studentAccount);
}

