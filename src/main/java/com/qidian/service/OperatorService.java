package com.qidian.service;

import com.qidian.domain.Operator;
import com.qidian.domain.Student;

import java.util.List;

public interface OperatorService {

    /**
     * 操作者登录
     * @param username
     * @param password
     * @return
     */
    public Operator login(String username, String password);

    public List<Operator> findAll();

    public Operator findByUsername(String username);

    public boolean save(Operator operator);


    public boolean update(Operator operator);


    public boolean delete(String username);

    public int deleteOperatorList(List<String> usernameList);

    public boolean changeStatus(String username, String status);
    public boolean updateInform(Operator operator);

}
