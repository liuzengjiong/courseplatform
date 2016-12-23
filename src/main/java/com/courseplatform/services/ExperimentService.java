package com.courseplatform.services;
/**
 * @TODO：
 * @fileName : com.courseplatform.services.ExperimentService.java
 * date | author | version |   
 * 2016年12月22日 | Jiong | 1.0 |
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.Experiment;

public interface ExperimentService {
    @Transactional
    boolean addExp(Experiment exp, String[] filepaths);

    /**
     * 获取教师信息
     *
     * @param account
     *         教师账号
     * @return 教师的信息
     */
    @Transactional
    String createCopymentByIdAndAcc(HttpServletRequest request,String id,String account);

    /**
     * 获取教师的课程列表
     *
     * @param account
     *         教师账号
     * @return
     */
    @Transactional
    List<Experiment> getExperimentList(String courseId,String account);

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


