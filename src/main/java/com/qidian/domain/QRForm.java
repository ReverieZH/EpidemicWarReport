package com.qidian.domain;

import com.qidian.utils.DateUtil;

import java.sql.Timestamp;

public class QRForm {
    private int qrformId;
    private String qrformName;
    private String qrformDesc;
    private Timestamp datetime;
    private String datetimeStr;
    private int questionnaireId;
    private String status;

    public int getQrformId() {
        return qrformId;
    }

    public void setQrformId(int qrformId) {
        this.qrformId = qrformId;
    }

    public String getQrformName() {
        return qrformName;
    }

    public void setQrformName(String qrformName) {
        this.qrformName = qrformName;
    }

    public String getQrformDesc() {
        return qrformDesc;
    }

    public void setQrformDesc(String qrformDesc) {
        this.qrformDesc = qrformDesc;
    }
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QRForm{" +
                "qrformId=" + qrformId +
                ", qrformName='" + qrformName + '\'' +
                ", qrformDesc='" + qrformDesc + '\'' +
                ", datetime=" + datetime +
                ", datetimeStr='" + datetimeStr + '\'' +
                ", questionnaireId=" + questionnaireId +
                ", status='" + status + '\'' +
                '}';
    }
}
