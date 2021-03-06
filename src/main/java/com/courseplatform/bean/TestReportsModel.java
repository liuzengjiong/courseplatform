package com.courseplatform.bean;

import java.util.Date;

public class TestReportsModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_reports_model.id
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_reports_model.course_id
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_reports_model.teacher_account
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    private String teacherAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_reports_model.filepath
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    private String filepath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_reports_model.time
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    private Date time;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_reports_model.id
     *
     * @return the value of test_reports_model.id
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_reports_model.id
     *
     * @param id the value for test_reports_model.id
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_reports_model.course_id
     *
     * @return the value of test_reports_model.course_id
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_reports_model.course_id
     *
     * @param courseId the value for test_reports_model.course_id
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_reports_model.teacher_account
     *
     * @return the value of test_reports_model.teacher_account
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public String getTeacherAccount() {
        return teacherAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_reports_model.teacher_account
     *
     * @param teacherAccount the value for test_reports_model.teacher_account
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public void setTeacherAccount(String teacherAccount) {
        this.teacherAccount = teacherAccount == null ? null : teacherAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_reports_model.filepath
     *
     * @return the value of test_reports_model.filepath
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_reports_model.filepath
     *
     * @param filepath the value for test_reports_model.filepath
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_reports_model.time
     *
     * @return the value of test_reports_model.time
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_reports_model.time
     *
     * @param time the value for test_reports_model.time
     *
     * @mbggenerated Fri Dec 09 17:24:58 CST 2016
     */
    public void setTime(Date time) {
        this.time = time;
    }
}