package com.qidian.service.impl;

import com.qidian.dao.QuestionDao;
import com.qidian.dao.QuestionnaireDao;
import com.qidian.domain.Question;
import com.qidian.domain.Questionnaire;
import com.qidian.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("questionnaireService")
@Transactional
public class QuestionnarieServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireDao questionnaireDao;

    @Override
    public List<Questionnaire> findAll() {
        return questionnaireDao.findAll();
    }

    @Override
    public List<Questionnaire> findAllByTaskId(int taskId) {
        return questionnaireDao.findAllByTaskId(taskId);
    }

    @Override
    public List<Questionnaire> findAllAndQuestion(int taskId) {
        return questionnaireDao.findAllAndQuestion(taskId);
    }

    @Override
    public Questionnaire findAndQuestion(int questionnaireId) {
        return questionnaireDao.findAndQuestion(questionnaireId);
    }

    @Override
    public Questionnaire findByQuestionnarieId(int questionnaireId) {
        return questionnaireDao.findByQuestionnarieId(questionnaireId);
    }

    @Override
    public List<Questionnaire> getQuestionnaire(String name, String status, Integer taskId) {
        return questionnaireDao.getQuestionnaireList(name,status,taskId);
    }

    @Override
    public boolean save(Questionnaire questionnaire) {
        return questionnaireDao.save(questionnaire);
    }

    @Override
    public boolean update(Questionnaire questionnaire) {
        return questionnaireDao.update(questionnaire);
    }

    @Override
    public boolean delete(int questionnarieId) {
       /* List<Question> questions=questionDao.findAllByQuestionnaireId(questionnarieId);
        questionDao.deleteList(questions);*/
        return questionnaireDao.delete(questionnarieId);
    }

    @Override
    public int deleteList(List<String> questionnairIdList) {
        return questionnaireDao.deleteList(questionnairIdList);
    }

    @Override
    public boolean changeStatus(int questionnarieId, String status) {
        return questionnaireDao.changeStatus(questionnarieId,status);
    }

}
