package com.qidian.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 问卷类Questionnaire（int questionnaireId String name String status
 * List<Questions> questionsList）
 */
public class Questionnaire implements Serializable {

    private int questionnaireId;            //编号
    private String queName;                    //名称
    private String queDesc;                    //描述
    private Timestamp datetime;             //创建时间
    private String status;                   //状态
    private List<Question> questionsList;  //问题数组
    private int taskId;                       //任务

    public Questionnaire(){}
    public Questionnaire(int questionnaireId){
        this.questionnaireId=questionnaireId;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQueName() {
        return queName;
    }

    public void setQueName(String queName) {
        this.queName = queName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getQueDesc() {
        return queDesc;
    }

    public void setQueDesc(String queDesc) {
        this.queDesc = queDesc;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "questionnaireId=" + questionnaireId +
                ", queName='" + queName + '\'' +
                ", queDesc='" + queDesc + '\'' +
                ", datetime=" + datetime +
                ", status='" + status + '\'' +
                ", questionsList=" + questionsList +
                ", taskId=" + taskId +
                '}';
    }
}
