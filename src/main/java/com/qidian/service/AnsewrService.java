package com.qidian.service;


import com.qidian.domain.Answer;
import com.qidian.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnsewrService {

    public int saveAnsewrs(List<Answer> answers);

    public int selectCountBySno(String sno, String startDate, String endDate);

    public List<Student> group(String academic,String majors,String className,String name,String type,int questionnaireId,String answerDate);
}
