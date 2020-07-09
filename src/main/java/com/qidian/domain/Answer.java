package com.qidian.domain;

import com.qidian.utils.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 回答类Answer（int answerId  Date answerDate String sno
 * Questionnaire questionnaire	Question question
 * String answer ）
 */
public class Answer implements Serializable {
    private int answerId;
    private Timestamp answerDate;
    private String answerDateStr;
    private String sno;
    private Questionnaire questionnaire;
    private Question question;
    private String stem;
    private String answer;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Timestamp getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Timestamp answerDate) {
        this.answerDate = answerDate;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerDateStr() {
        if(answerDate!=null){
            answerDateStr= DateUtil.date2String(answerDate,"yyyy-MM-dd HH:mm");
        }
        return answerDateStr;
    }

    public void setAnswerDateStr(String answerDateStr) {
        this.answerDateStr = answerDateStr;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answerDate=" + answerDate +
                ", answerDateStr='" + answerDateStr + '\'' +
                ", sno='" + sno + '\'' +
                ", questionnaire=" + questionnaire +
                ", question=" + question +
                ", stem='" + stem + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
