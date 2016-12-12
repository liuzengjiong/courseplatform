package com.courseplatform.services;

import com.courseplatform.bean.Courseware;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/12.
 */
public interface CoursewareService {

    /**
     * 获取课件列表
     *
     * @param courseId
     *         课程ID
     * @param teacheraccount
     *         教师账号
     * @return
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 14:24
     */
    @Transactional
    List<Courseware> getCourseware(String courseId, String teacheraccount);

    /**
     * 添加课件
     *
     * @param teacherAccount
     * @param courseId
     * @param filepaths
     */
    @Transactional
    int addCoursewares(String teacherAccount, String courseId, String[] filepaths);

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
}
