package com.qidian.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 操作员类Operatorb（String username   String password   String role
 * String roleStr String department String valiaity String valiaityStr）
 */
public class Operator implements Serializable {

    private int operatorId;        //操作员id
    private String username;        //用户名
    private String password;        //密码
    private String name;
    private String role;            //角色：0系统管理员、1学生处管理人员、2学院管理人员、3辅导员
    private String roleStr;         //0系统管理员、1学生处管理人员、2学院管理人员、3辅导员
    private String academicName;      //部门
    private String validty;        //有效性，0---无效，1---有效
    private String validityStr;     //0---无效，1---有效
    private List<Task> taskList;    //任务数组

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleStr() {
        if(role != null){
            if("0".equals(role)){
                roleStr = "系统管理员";
            }
            if("1".equals(role)){
                roleStr = "学生处管理人员";
            }
            if("2".equals(role)){
                roleStr = "学院管理人员";
            }
            if("3".equals(role)){
                roleStr = "辅导员";
            }
        }
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public String getValidty() {
        return validty;
    }

    public void setValidty(String validty) {
        this.validty = validty;
    }

    public String getValidityStr() {
        if(validty != null) {
            if ("0".equals(validty)) {
                validty = "无效";
            }
            if ("1".equals(validty)) {
                validty = "有效";
            }
        }
        return validityStr;
    }

    public void setValidityStr(String validityStr) {
        this.validityStr = validityStr;
    }


    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "operatorId=" + operatorId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", roleStr='" + roleStr + '\'' +
                ", academicName='" + academicName + '\'' +
                ", validty='" + validty + '\'' +
                ", validityStr='" + validityStr + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
