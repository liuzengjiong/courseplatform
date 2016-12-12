package com.courseplatform.services.impl;

import com.courseplatform.bean.Courseware;
import com.courseplatform.dao.CoursewareMapper;
import com.courseplatform.services.CoursewareService;
import com.courseplatform.util.IDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chen cy
 * Created by ye on 2016/12/12.
 */
@Service
public class CoursewareServiceImpl implements CoursewareService {

    @Autowired
    private CoursewareMapper coursewareMapper;

    @Override
    public List<Courseware> getCourseware(String courseId, String teacheraccount) {
        return coursewareMapper.selectByCourseIdAndTeacherAccount(courseId, teacheraccount);
    }

    @Override
    public int addCoursewares(String teacherAccount, String courseId, String[] filepaths) {
        List<Courseware> coursewares = new ArrayList<>();
        for (String filepath : filepaths) {
            Courseware courseware = new Courseware(IDFactory.newID(), courseId, teacherAccount, filepath);
        }
        return coursewareMapper.insertCoursewares(coursewares);
    }

    @Override
    public int deleteCourseware(String coursewareId) {
        return coursewareMapper.deleteByPrimaryKey(coursewareId);
    }
}
