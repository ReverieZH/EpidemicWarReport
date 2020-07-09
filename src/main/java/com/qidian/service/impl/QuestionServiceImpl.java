package com.qidian.service.impl;

import com.qidian.dao.QuestionDao;
import com.qidian.domain.Question;
import com.qidian.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("QuestionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public List<Question> findAllByQuestionnaireId(int questionnaireId) {
        return questionDao.findAllByQuestionnaireId(questionnaireId);
    }

    @Override
    public Question findByQuestionId(int questionId) {
        return questionDao.findByQuestionId(questionId);
    }

    @Override
    public boolean save(Question question) {
        return questionDao.save(question);
    }

    @Override
    public boolean update(Question question) {
        return questionDao.update(question);
    }

    @Override
    public boolean delete(int questionId) {
        return questionDao.delete(questionId);
    }

    @Override
    public int deleteList(List<String> questionIdList) {
        return questionDao.deleteList(questionIdList);
    }

    @Override
    public boolean changeStatus(int questionId, String validity) {
        return questionDao.changeStatus(questionId,validity);
    }
}
