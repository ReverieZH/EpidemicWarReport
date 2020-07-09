package com.qidian.service.impl;

import com.qidian.dao.ApplyDao;
import com.qidian.dao.OperatorDao;
import com.qidian.domain.Apply;
import com.qidian.domain.Task;
import com.qidian.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("applyService")
@Transactional
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyDao applyDao;

    @Override
    public List<Apply> findAll() {
        return applyDao.findAll();
    }

    @Override
    public List<String> findAllName() {
        return applyDao.findAllName();
    }

    @Override
    public List<Apply> findAllByOperator(String username) {
        return applyDao.findAllByOperator(username);
    }

    @Override
    public Apply findByApplyId(int applyId) {
        return applyDao.findByApplyId(applyId);
    }

    @Override
    public boolean save(Apply apply) {
        return applyDao.save(apply);
    }

    @Override
    public boolean update(Apply apply) {
        return applyDao.update(apply);
    }

    @Override
    public boolean delete(int applyId) {
        return applyDao.delete(applyId);
    }

    @Override
    public int deleteApplyList(List<String> applyIdList) {
        return applyDao.deleteApplyList(applyIdList);
    }

    @Override
    public boolean changeStatus(int applyId, String status) {
        return applyDao.changeStatus(applyId,status);
    }

    @Override
    public List<Apply> group() {
        return applyDao.group();
    }
}
