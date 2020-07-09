package com.qidian.domain;

import java.io.Serializable;

/**
 * 学校类School（int schoolId String schoolName）
 */
public class School implements Serializable {

    private int schoolId;       //学校id
    private String schoolName;  //学校名称

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
