package com.courseplatform.dao;

import com.courseplatform.bean.Course;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface CourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Delete({
        "delete from course",
        "where course_id = #{courseId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String courseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Insert({
        "insert into course (course_id, course_name, ",
        "time, course_introduction, ",
        "course_syllabus)",
        "values (#{courseId,jdbcType=VARCHAR}, #{courseName,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP}, #{courseIntroduction,jdbcType=LONGVARCHAR}, ",
        "#{courseSyllabus,jdbcType=LONGVARCHAR})"
    })
    int insert(Course record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Select({
        "select",
        "course_id, course_name, time, course_introduction, course_syllabus",
        "from course",
        "where course_id = #{courseId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="course_name", property="courseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="course_introduction", property="courseIntroduction", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="course_syllabus", property="courseSyllabus", jdbcType=JdbcType.LONGVARCHAR)
    })
    Course selectByPrimaryKey(String courseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Select({
        "select",
        "course_id, course_name, time, course_introduction, course_syllabus",
        "from course"
    })
    @Results({
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="course_name", property="courseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="course_introduction", property="courseIntroduction", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="course_syllabus", property="courseSyllabus", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Course> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Update({
        "update course",
        "set course_name = #{courseName,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "course_introduction = #{courseIntroduction,jdbcType=LONGVARCHAR},",
          "course_syllabus = #{courseSyllabus,jdbcType=LONGVARCHAR}",
        "where course_id = #{courseId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Course record);
}