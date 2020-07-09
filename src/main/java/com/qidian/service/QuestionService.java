package com.qidian.service;

import com.qidian.domain.Question;

import java.util.List;

public interface QuestionService {
    public List<Question> findAll();

    public List<Question> findAllByQuestionnaireId(int questionnaireId);

    public Question findByQuestionId(int questionId);

    public boolean save(Question question);

    public boolean update(Question question);

    public boolean delete(int questionId);

    public int deleteList(List<String> questionIdList);

    public boolean changeStatus(int questionId,String validity);
}
