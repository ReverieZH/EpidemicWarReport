package com.qidian.service.impl;

import com.qidian.dao.FormDao;
import com.qidian.domain.Form;
import com.qidian.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("formService")
@Transactional
public class FormServiceImpl implements FormService {

    @Autowired
    private FormDao formDao;

    @Override
    public List<Form> findAll() {
        return formDao.findAll();
    }

    @Override
    public List<Form> findAllByApplyName(String applyName) {
        return formDao.findAllByApplyName(applyName);
    }

    @Override
    public Form findByFormId(int formId) {
        return formDao.findByFormId(formId);
    }

    @Override
    public boolean save(Form form) {
        return formDao.save(form);
    }

    @Override
    public boolean update(Form form) {
        return formDao.update(form);
    }

    @Override
    public boolean delete(int formId) {
        return formDao.delete(formId);
    }

    @Override
    public int deleteFormList(List<String> formIdList) {
        return formDao.deleteFormList(formIdList);
    }

    @Override
    public boolean changeStatus(int formId, String status) {
        return formDao.changeStatus(formId,status);
    }
}
