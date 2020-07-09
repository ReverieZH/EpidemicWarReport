package com.qidian.service;

import com.qidian.domain.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    public List<Questionnaire> findAll();

    public List<Questionnaire> findAllByTaskId(int taskId);

    public List<Questionnaire>  findAllAndQuestion(int taskId);

    public Questionnaire  findAndQuestion(int questionnaireId);


    public Questionnaire findByQuestionnarieId(int questionnarieId);

    public List<Questionnaire> getQuestionnaire(String name, String status, Integer taskId);

    public boolean save(Questionnaire questionnaire);

    public boolean update(Questionnaire questionnaire);

    public boolean delete(int questionnarieId);

    public int deleteList(List<String> questionnairIdList);

    public boolean changeStatus(int questionnarieId,String status);
}
