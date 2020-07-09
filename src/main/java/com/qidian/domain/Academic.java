package com.qidian.domain;

public class Academic {
    private Integer academicId;
    private String academicName;
    private Integer schoolId;
    private Integer campusId;

    private String status;

    public Integer getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Integer academicId) {
        this.academicId = academicId;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Academic{" +
                "academicId=" + academicId +
                ", academicName='" + academicName + '\'' +
                ", schoolId=" + schoolId +
                ", campusId=" + campusId +
                '}';
    }
}
