package com.qidian.service.impl;

import com.qidian.dao.QuestionnaireDao;
import com.qidian.dao.TaskDao;
import com.qidian.domain.Questionnaire;
import com.qidian.domain.Task;
import com.qidian.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public List<Task> findAllByOperator(String username) {
        return taskDao.findAllByOperator(username);
    }

    @Override
    public Task findByTaskId(int taskId) {
        return taskDao.findByTaskId(taskId);
    }

    @Override
    public List<Task> getTaskList(String taskName, String taskDesc, String startTime, String endTime) {
        return taskDao.getTaskList(taskName,taskDesc,startTime,endTime);
    }

    @Override
    public boolean save(Task task) {
        return  taskDao.save(task);
    }

    @Override
    public boolean update(Task task) {
        return taskDao.update(task);
    }

    @Override
    public boolean delete(int taskId) {
       /* List<Questionnaire> questionnaires = questionnaireDao.findAllByTaskId(taskId);
        questionnaireDao.deleteList(questionnaires);*/
        return taskDao.delete(taskId);
    }

    @Override
    public boolean changeStatus(int taskId, String status) {
        return taskDao.changeStatus(taskId,status);
    }

    @Override
    public int deleteTaskList(List<String> taskIdList) {
        return taskDao.deleteTaskList(taskIdList);
    }
}
