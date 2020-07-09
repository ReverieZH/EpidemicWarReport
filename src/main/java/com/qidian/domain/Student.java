package com.qidian.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 学生类Student（String sno String password String name String sex
 * String grade String type String class String majors String department
 * String dormitory String origin String address
 * String status String statusStr）
 */
public class Student implements Serializable {

    private String sno;         //学号
    private String password;    //密码
    private String name;        //姓名
    private String sex;         //性别
    private String grade;       //年级
    private String type;        //类型
    private String classesName;   //班级
    private String majorName;      //专业
    private String academicName;  //学院
    private String dormitory;   //宿舍号
    private String origin;      //生源地
    private String address;     //家庭地址
    private String schoolName;   //学校名称
    private String phone;
    private String status;      //状态：0---非正常状态，1---正常状态
    private String statusStr;   //0---非正常状态，1---正常状态

    List<Answer> answers;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatusStr() {
        if(status != null){
            if("0".equals(status)){
                statusStr = "非正常状态";
            }
            if("1".equals(status)){
                statusStr = "正常状态";
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String toString2() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", answers=" + answers +
                '}';
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                ", type='" + type + '\'' +
                ", classesName='" + classesName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", academicName='" + academicName + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", origin='" + origin + '\'' +
                ", address='" + address + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
