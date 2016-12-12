package com.courseplatform.bean;

import org.apache.ibatis.type.Alias;

@Alias("Courseware")
public class Courseware {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware.courseware_id
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    private String coursewareId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware.course_id
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware.teacher_account
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    private String teacherAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseware.courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    private String courseware;

    public Courseware(String coursewareId, String courseId, String teacherAccount, String courseware) {
        this.coursewareId = coursewareId;
        this.courseId = courseId;
        this.teacherAccount = teacherAccount;
        this.courseware = courseware;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware.courseware_id
     *
     * @return the value of courseware.courseware_id
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public String getCoursewareId() {
        return coursewareId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware.courseware_id
     *
     * @param coursewareId the value for courseware.courseware_id
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public void setCoursewareId(String coursewareId) {
        this.coursewareId = coursewareId == null ? null : coursewareId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware.course_id
     *
     * @return the value of courseware.course_id
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware.course_id
     *
     * @param courseId the value for courseware.course_id
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware.teacher_account
     *
     * @return the value of courseware.teacher_account
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public String getTeacherAccount() {
        return teacherAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware.teacher_account
     *
     * @param teacherAccount the value for courseware.teacher_account
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public void setTeacherAccount(String teacherAccount) {
        this.teacherAccount = teacherAccount == null ? null : teacherAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseware.courseware
     *
     * @return the value of courseware.courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public String getCourseware() {
        return courseware;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseware.courseware
     *
     * @param courseware the value for courseware.courseware
     *
     * @mbggenerated Sat Dec 10 22:49:05 CST 2016
     */
    public void setCourseware(String courseware) {
        this.courseware = courseware == null ? null : courseware.trim();
    }
}