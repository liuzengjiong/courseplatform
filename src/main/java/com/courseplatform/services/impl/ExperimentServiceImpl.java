package com.courseplatform.services.impl;
/**
 * @TODO：
 * @fileName : com.courseplatform.services.impl.ExperimentServiceImpl.java
 * date | author | version |   
 * 2016年12月22日 | Jiong | 1.0 |
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.bean.Course;
import com.courseplatform.bean.Experiment;
import com.courseplatform.bean.User;
import com.courseplatform.dao.CourseMapper;
import com.courseplatform.dao.CoursewareMapper;
import com.courseplatform.dao.ExperimentMapper;
import com.courseplatform.dao.StudentCourseMapper;
import com.courseplatform.dao.TeacherCourseMapper;
import com.courseplatform.dao.UserMapper;
import com.courseplatform.handler.TeacherHandler;
import com.courseplatform.services.ExperimentService;
import com.courseplatform.util.FileUtil;
import com.courseplatform.util.IDFactory;

@Service
public class ExperimentServiceImpl implements ExperimentService {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherHandler.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursewareMapper coursewareMapper;
    @Autowired
    private ExperimentMapper experimentMapper;

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
    public boolean addExp(Experiment exp, String[] filepaths) {
    	//List<StudentCourse> students = studentCourseMapper.selectByCourseId(exp.getCourseId());
        //User user = userMapper.selectByPrimaryKey(account);
        Course course = courseMapper.selectByPrimaryKey(exp.getCourseId());
        String filepath = "/uploadFile/" + exp.getUserAccount() + "/" + exp.getCourseId() + "/" + exp.getId() + "/" + filepaths[0];
        exp.setFileUrl(filepath);
        int i = experimentMapper.insert(exp);//courseMapper.insert(course);
        

      /*  // 设置该老师的课程
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourseId(course.getCourseId());
        teacherCourse.setTeacherAccount(account);
        int j = teacherCourseMapper.insert(teacherCourse);

        // 设置老师的课件
        List<Courseware> coursewares = new ArrayList<>();
        for (String filename : filenames) {
            String filepath = "/uploadFile/" + account + "/" + course.getCourseId() + "/" + filename;
            LOG.info("filename:" + filename + ",filePath:" + filepath);
            Courseware courseware = new Courseware(IDFactory.newID(), teacherCourse.getCourseId(), teacherCourse.getTeacherAccount(), filename, filepath);
            coursewares.add(courseware);
//            coursewareMapper.insert(courseware);
        }
        if (null != filenames && filenames.length > 0) {
            int k = coursewareMapper.insertCoursewares(coursewares);
        }
        LOG.info("i:" + i + ",j:" + j);*/
//        LOG.info("i:" + i + ",j:" + j );
        LOG.info("return :"+i);
        return i==1;
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
    public String createCopymentByIdAndAcc(HttpServletRequest request,String id,String account) {
    	User user = userMapper.selectByPrimaryKey(account);
    	if(user.getType()==1){ //学生查看
    		Experiment exp = experimentMapper.selectByCloneIdAndAccout(id, account);
    		if(exp==null){//没有打开过，那就为学生创建一份副本
        		exp = experimentMapper.selectByPrimaryKey(id);
        		String path = request.getSession().getServletContext().getRealPath("/");
        		String filePath = exp.getFileUrl();
        		filePath = path+"/"+filePath;
        		String newFilepath = "/uploadFile/" + account + "/" + exp.getCourseId() + "/" + id + "/" + filePath.substring(filePath.lastIndexOf("/")+1);
        		String realNewFilepath = path + newFilepath;
        		if(FileUtil.copyFile(filePath, realNewFilepath, true)){
        			Experiment newExp = new Experiment();
        			newExp.setId(IDFactory.newID());
        			newExp.setCloneId(id);
        			newExp.setCourseId(exp.getCourseId());
        			newExp.setUserAccount(account);
        			newExp.setUpdateTime(new Date());
        			newExp.setFileUrl(newFilepath);
        			newExp.setExperimentName(exp.getExperimentName());
        			int i = experimentMapper.insert(newExp);
        			return newFilepath;
        		}else{
        			System.out.println("文件复制失败");
        		}
        	}else{
        		return exp.getFileUrl();
        	}
    	}else{
    		Experiment exp = experimentMapper.selectByPrimaryKey(id);
    		return exp.getFileUrl();
    	}

    	return null;
    }

    /**
     * 获取课程下的实验报告
     *
     * @param account
     *         账号
     * @return 
     * @author Jiong
     * @date 2016年12月23日02:36:25
     */
    @Override
    public List<Experiment> getExperimentList(String courseId,String account) {
    	User user = userMapper.selectByPrimaryKey(account);
    	if(user.getType()==1){ //学生查看
    		Course sc = studentCourseMapper.selectCourseByStudentAndCourseId(courseId, account);
    		if(sc==null){ //学生不在这门课程下
    			return new ArrayList<Experiment>();
    		}
//    		Experiment exp = experimentMapper.selectByAccountAndCourseId(account, courseId);
//    		if(exp==null){ //学生还没查看过这门课程
//    			
//    		}
    	}
        return experimentMapper.selectByCourseId(courseId);
    }

    @Override
    public Course getCourse(String courseId, String account) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public int deleteCourse(String courseId, String account) {
        return teacherCourseMapper.deleteTeacherAccount(courseId, account);
    }
}


