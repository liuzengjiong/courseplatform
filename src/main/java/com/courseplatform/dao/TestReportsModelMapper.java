package com.courseplatform.dao;

import com.courseplatform.bean.TestReportsModel;
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
public interface TestReportsModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_reports_model
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Delete({
        "delete from test_reports_model",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_reports_model
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Insert({
        "insert into test_reports_model (id, course_id, ",
        "teacher_account, filepath, ",
        "time)",
        "values (#{id,jdbcType=VARCHAR}, #{courseId,jdbcType=VARCHAR}, ",
        "#{teacherAccount,jdbcType=VARCHAR}, #{filepath,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP})"
    })
    int insert(TestReportsModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_reports_model
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Select({
        "select",
        "id, course_id, teacher_account, filepath, time",
        "from test_reports_model",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacher_account", property="teacherAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="filepath", property="filepath", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    TestReportsModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_reports_model
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Select({
        "select",
        "id, course_id, teacher_account, filepath, time",
        "from test_reports_model"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacher_account", property="teacherAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="filepath", property="filepath", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TestReportsModel> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_reports_model
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    @Update({
        "update test_reports_model",
        "set course_id = #{courseId,jdbcType=VARCHAR},",
          "teacher_account = #{teacherAccount,jdbcType=VARCHAR},",
          "filepath = #{filepath,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TestReportsModel record);
}