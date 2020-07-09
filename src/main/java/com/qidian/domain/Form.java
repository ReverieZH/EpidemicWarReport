package  com.qidian.domain;


import java.util.Map;

public class Form {

  private int formId;
  private String formName;
  private String status;
  private String applyName;
  private String link;

  private Map<String,String> group;

  public int getFormId() {
    return formId;
  }

  public void setFormId(int formId) {
    this.formId = formId;
  }


  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getApplyName() {
    return applyName;
  }

  public void setApplyName(String applyName) {
    this.applyName = applyName;
  }

  public String getLink() {
    return link;
  }

  public String getSnoLink(String sno){
    if(link.endsWith("&sno=")){
      return link+sno;
    }else {
      return link;
    }
  }


  public void setLink(String link) {
    this.link = link;
  }

  public Map<String, String> getGroup() {
    return group;
  }

  public void setGroup(Map<String, String> group) {
    this.group = group;
  }

  @Override
  public String toString() {
    return "Form{" +
            "formId=" + formId +
            ", formName='" + formName + '\'' +
            ", status='" + status + '\'' +
            ", applyName=" + applyName +
            ", link=" + link +
            '}';
  }
}
