package com.courseplatform.dao;

import com.courseplatform.bean.Courseware;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CoursewareMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    @Delete({
            "delete from courseware",
            "where courseware_id = #{coursewareId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String coursewareId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    @Insert({
            "insert into courseware (courseware_id, course_id, ",
            "teacher_account, courseware)",
            "values (#{coursewareId,jdbcType=VARCHAR}, #{courseId,jdbcType=VARCHAR}, ",
            "#{teacherAccount,jdbcType=VARCHAR}, #{courseware,jdbcType=VARCHAR})"
    })
    int insert(Courseware record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    @Select({
            "select",
            "courseware_id, course_id, teacher_account, courseware",
            "from courseware",
            "where courseware_id = #{coursewareId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "courseware_id", property = "coursewareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "course_id", property = "courseId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "teacher_account", property = "teacherAccount", jdbcType = JdbcType.VARCHAR),
            @Result(column = "courseware", property = "courseware", jdbcType = JdbcType.VARCHAR)
    })
    Courseware selectByPrimaryKey(String coursewareId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    @Select({
            "select",
            "courseware_id, course_id, teacher_account, courseware",
            "from courseware"
    })
    @Results({
            @Result(column = "courseware_id", property = "coursewareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "course_id", property = "courseId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "teacher_account", property = "teacherAccount", jdbcType = JdbcType.VARCHAR),
            @Result(column = "courseware", property = "courseware", jdbcType = JdbcType.VARCHAR)
    })
    List<Courseware> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    @Update({
            "update courseware",
            "set course_id = #{courseId,jdbcType=VARCHAR},",
            "teacher_account = #{teacherAccount,jdbcType=VARCHAR},",
            "courseware = #{courseware,jdbcType=VARCHAR}",
            "where courseware_id = #{coursewareId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Courseware record);

    /**
     * 查询教师teacherAccount的courseId课程的所有课件文件
     *
     * @param courseId
     *         课程id
     * @param teacherAccount
     *         教师账号
     * @return 教师teacherAccount的courseId课的课件文件列表
     * @author ye [15622797401@163.com]
     * @date 2016/12/11 0:31
     */
    List<Courseware> selectByCourseIdAndTeacherAccount(@Param("courseId") String courseId, @Param("teacherAccount") String teacherAccount);

    int insertCoursewares(@Param("coursewares") List<Courseware> coursewares);

}