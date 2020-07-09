package com.qidian.service.impl;

import com.qidian.dao.OperatorDao;
import com.qidian.domain.Operator;
import com.qidian.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("operatorService")
@Transactional
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorDao operatorDao;

    @Override
    public Operator login(String username, String password) {
        return operatorDao.login(username,password);
    }

    @Override
    public List<Operator> findAll() {
        return operatorDao.findAll();
    }

    @Override
    public Operator findByUsername(String username) {
        return operatorDao.findByUsername(username);
    }

    @Override
    public boolean save(Operator operator) {
        return operatorDao.save(operator);
    }

    @Override
    public boolean update(Operator operator) {
        return operatorDao.update(operator);
    }

    @Override
    public boolean delete(String username) {
        return operatorDao.delete(username);
    }

    @Override
    public int deleteOperatorList(List<String> usernameList) {
        return operatorDao.deleteOperatorList(usernameList);
    }

    @Override
    public boolean changeStatus(String username, String status) {
        return operatorDao.changeStatus(username,status);
    }

    @Override
    public boolean updateInform(Operator operator) {
        return operatorDao.updateInform(operator);
    }
}
