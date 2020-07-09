package com.qidian.domain;

import com.qidian.utils.DateUtil;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * 任务类
 */
public class Task implements Serializable {

    private int taskId;                             //任务id
    private String taskName;                        //任务名称
    private String taskDesc;                        //任务描述
    private Timestamp datetime;                          //发布时间
    private String datetimeStr;
    private String status;
    private Operator operator;                      //操作员
    private List<Questionnaire> questionnaireList;  //问卷数组

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getDatetimeStr() {
        if(datetime!=null){
            datetimeStr= DateUtil.date2String(datetime,"yyyy-MM-dd HH:mm");
        }
        return datetimeStr;
    }

    public void setDatetimeStr(String datetimeStr) {
        this.datetimeStr = datetimeStr;
    }

    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", datetime=" + datetime +
                ", datetimeStr='" + datetimeStr + '\'' +
                ", status='" + status + '\'' +
                ", operator=" + operator +
                ", questionnaireList=" + questionnaireList +
                '}';
    }
}
