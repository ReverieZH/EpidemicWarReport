package com.qidian.domain;


import java.util.List;

public class Apply {

  private int applyId;
  private String applyName;
  private String status;
  private String username;

  List<Form> formList;

  public int getApplyId() {
    return applyId;
  }

  public void setApplyId(int applyId) {
    this.applyId = applyId;
  }


  public String getApplyName() {
    return applyName;
  }

  public void setApplyName(String applyName) {
    this.applyName = applyName;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Form> getFormList() {
    return formList;
  }

  public void setFormList(List<Form> formList) {
    this.formList = formList;
  }

  @Override
  public String toString() {
    return "Apply{" +
            "applyId=" + applyId +
            ", applyName='" + applyName + '\'' +
            ", status='" + status + '\'' +
            ", username='" + username + '\'' +
            '}';
  }
}
