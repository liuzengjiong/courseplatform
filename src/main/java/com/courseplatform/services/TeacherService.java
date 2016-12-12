package com.courseplatform.services;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.Courseware;
import com.courseplatform.bean.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/11.
 */
public interface TeacherService {
    @Transactional
    boolean addCourse(String account, Course course, String[] filepaths);

    /**
     * 获取教师信息
     *
     * @param account
     *         教师账号
     * @return 教师的信息
     */
    @Transactional
    User getTeacherInfo(String account);

    /**
     * 获取教师的课程列表
     *
     * @param account
     *         教师账号
     * @return
     */
    @Transactional
    List<Course> getCourseList(String account);

    /**
     * 获取课程的详细信息
     *
     * @param courseId
     *         课程Id
     * @param account
     *         教师账号
     * @return
     */
    @Transactional
    Course getCourse(String courseId, String account);

    @Transactional
    List<Courseware> getCourseware(String courseId, String account);

    @Transactional
    void addCoursewares(String account, String courseId, String[] filepaths);

    /**
     * 删除课件
     *
     * @param coursewareId
     *         课件id
     * @return 是否删除成功
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 12:38
     */
    @Transactional
    int deleteCourseware(String coursewareId);

    /**
     * 删除老师的课程
     *
     * @param courseId
     *         课程Id
     * @param account
     *         账号
     * @return 是否删除成功
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 12:48
     */
    @Transactional
    int deleteCourse(String courseId, String account);


}
