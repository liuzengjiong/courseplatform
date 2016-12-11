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

    @Transactional
    User getTeacherInfo(String account);

    @Transactional
    List<Course> getCourseList(String account);

    @Transactional
    List<Courseware> getCourseware(String courseId, String account);

    @Transactional
    void addCoursewares(String account, String courseId, String[] filepaths);
}
