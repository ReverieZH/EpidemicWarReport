package com.qidian.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题类（Question（int questionId String type   String stem String option1
 * String option2       String option3 String option4  String option5
 * Questionnaire questionnaire  String validity））
 */
public class Question implements Serializable {

    private int questionId;                 //问题id
    private String type;                     //问题类型
    private String stem;                     //题干
    private String option1;                  //选项1
    private String option2;                  //选项2
    private String option3;                  //选项3
    private String option4;                  //选项4
    private String option5;                  //选项5
    private int questionnaireId;     //问卷
    private String validity;                 //有效性:0---无效，1---有效
    private String validityStr;              //0---无效，1---有效

    List<String> optionlist;

    public Question() {
    }

    public Question(String stem){
        this.stem=stem;
    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getValidityStr() {
        if(validity != null){
            if("0".equals(validity)){
                validityStr = "无效";
            }
            if("1".equals(validity)){
                validityStr = "有效";
            }
        }
        return validityStr;
    }

    public void setValidityStr(String validityStr) {
        this.validityStr = validityStr;
    }

    public String getTypeStr(){
        switch (type){
            case "0":return "输入框";
            case "1":return "下拉框";
            case "2":return "地址框";
            case "3":return "定位框";
            case "4":return "选择框";
        }
        return "";
    }

    public List<String> getOptionlist() {
        optionlist=new ArrayList<>();
        if(!option1.equals("")&&option1!=null){
            optionlist.add(option1);
        }
        if(!option2.equals("")&&option2!=null){
            optionlist.add(option2);
        }
        if(!option3.equals("")&&option3!=null){
            optionlist.add(option3);
        }
        if(!option4.equals("")&&option4!=null){
            optionlist.add(option4);
        }
        if(!option5.equals("")&&option5!=null){
            optionlist.add(option5);
        }
        return optionlist;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionId=" + questionId +
                ", type='" + type + '\'' +
                ", stem='" + stem + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", option5='" + option5 + '\'' +
                ", questionnaireId=" + questionnaireId +
                ", validity='" + validity + '\'' +
                ", validityStr='" + validityStr + '\'' +
                '}';
    }
}
