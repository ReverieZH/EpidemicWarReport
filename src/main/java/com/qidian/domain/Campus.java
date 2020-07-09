package com.qidian.domain;

public class Campus  {
    private Integer campusId;
    private String  campusName;
    private Integer schoolId;
    private String status;
    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "campusId=" + campusId +
                ", campusName='" + campusName + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
