package com.qidian.service.impl;

import com.qidian.dao.AnswerDao;
import com.qidian.domain.Answer;
import com.qidian.domain.Student;
import com.qidian.service.AnsewrService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("ansewrService")
@Transactional
public class AnsewrServiceImpl implements AnsewrService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public int saveAnsewrs(List<Answer> answers) {
        return answerDao.saveAnsewrs(answers);
    }

    @Override
    public int selectCountBySno(String sno, String startDate, String endDate) {
        return answerDao.selectCountBySno(sno,startDate,endDate);
    }

    @Override
    public List<Student> group(String academic,String majors,String className,String name,String type,int questionnaireId,String answerDate) {
        return answerDao.group(academic, majors,className,name, type,questionnaireId,answerDate);
    }
}
