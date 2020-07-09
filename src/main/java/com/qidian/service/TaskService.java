package com.qidian.service;

import com.qidian.domain.Task;

import java.util.List;

public interface TaskService {
    public List<Task> findAll();
    public List<Task>  findAllByOperator(String username);

    public Task findByTaskId(int taskId);


    public List<Task> getTaskList( String taskName, String taskDesc, String startTime, String endTime);

    public boolean save(Task task);

    public boolean update(Task task);

    public boolean delete(int taskId);

    public boolean changeStatus(int taskId,String status);

    public int deleteTaskList(List<String> taskIdList);
}
