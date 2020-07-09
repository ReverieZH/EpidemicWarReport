package com.qidian.domain;

public class Classes {
    private Integer classesId;
    private String classesName;
    private Integer majorId;
    private String majorName;
    private Integer academicId;
    private String status;

    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Integer academicId) {
        this.academicId = academicId;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classesId=" + classesId +
                ", classesName='" + classesName + '\'' +
                ", majorId=" + majorId +
                ", status='" + status + '\'' +
                '}';
    }
}
