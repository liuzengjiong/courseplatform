package com.courseplatform.dao;

import com.courseplatform.bean.Experiment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ExperimentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table experiment
     *
     * @mbggenerated Fri Dec 23 06:08:25 CST 2016
     */
    @Delete({
        "delete from experiment",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table experiment
     *
     * @mbggenerated Fri Dec 23 06:08:25 CST 2016
     */
    @Insert({
        "insert into experiment (id, experiment_name, ",
        "course_id, file_url, ",
        "update_time, user_account, ",
        "deadline, state, ",
        "ps, clone_id)",
        "values (#{id,jdbcType=VARCHAR}, #{experimentName,jdbcType=VARCHAR}, ",
        "#{courseId,jdbcType=VARCHAR}, #{fileUrl,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{userAccount,jdbcType=VARCHAR}, ",
        "#{deadline,jdbcType=TIMESTAMP}, #{state,jdbcType=INTEGER}, ",
        "#{ps,jdbcType=VARCHAR}, #{cloneId,jdbcType=VARCHAR})"
    })
    int insert(Experiment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table experiment
     *
     * @mbggenerated Fri Dec 23 06:08:25 CST 2016
     */
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    Experiment selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table experiment
     *
     * @mbggenerated Fri Dec 23 06:08:25 CST 2016
     */
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    List<Experiment> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table experiment
     *
     * @mbggenerated Fri Dec 23 06:08:25 CST 2016
     */
    @Update({
        "update experiment",
        "set experiment_name = #{experimentName,jdbcType=VARCHAR},",
          "course_id = #{courseId,jdbcType=VARCHAR},",
          "file_url = #{fileUrl,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "user_account = #{userAccount,jdbcType=VARCHAR},",
          "deadline = #{deadline,jdbcType=TIMESTAMP},",
          "state = #{state,jdbcType=INTEGER},",
          "ps = #{ps,jdbcType=VARCHAR},",
          "clone_id = #{cloneId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Experiment record);
    
    
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment",
        "where user_account = #{account,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    Experiment selectByAccount(String account);
    
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment",
        "where user_account = #{account,jdbcType=VARCHAR}",
        "and course_id = #{courseId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    Experiment selectByAccAndCourseId(String courseId,String account);
    
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment",
        "where clone_id = #{0}",
        "and user_account = #{1}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    Experiment selectByCloneIdAndAccout(String cloneId,String account);
    
    
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment",
        "where course_id = #{courseId,jdbcType=VARCHAR}",
        "and clone_id is null"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    List<Experiment> selectByCourseId(String courseId);
    @Select({
        "select",
        "id, experiment_name, course_id, file_url, update_time, user_account, deadline, ",
        "state, ps, clone_id",
        "from experiment",
        "where clone_id = #{cloneId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="experiment_name", property="experimentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_url", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_account", property="userAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="deadline", property="deadline", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ps", property="ps", jdbcType=JdbcType.VARCHAR),
        @Result(column="clone_id", property="cloneId", jdbcType=JdbcType.VARCHAR)
    })
    List<Experiment> selectByCloneId(String cloneId);
}