package com.qidian.service.impl;

import com.qidian.dao.QRFormDao;
import com.qidian.domain.QRForm;
import com.qidian.domain.Task;
import com.qidian.service.QRFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("qrFormService")
@Transactional
public class QRFormServiceImpl implements QRFormService {

    @Autowired
    private QRFormDao qrFormDao;

    @Override
    public List<QRForm> findAll() {
        return qrFormDao.findAll();
    }

    @Override
    public QRForm findByQrformId(int qrformId) {
        return qrFormDao.findByQrformId(qrformId);
    }

    @Override
    public boolean save(QRForm qrForm) {
        return qrFormDao.save(qrForm);
    }

    @Override
    public boolean update(QRForm qrForm) {
        return qrFormDao.update(qrForm);
    }

    @Override
    public boolean delete(int qrformId) {
        return qrFormDao.delete(qrformId);
    }

    @Override
    public int deleteQrFormList(List<String> qrformIdList) {
        return qrFormDao.deleteQrFormList(qrformIdList);
    }

    @Override
    public boolean changeStatus(int qrformId, String status) {
        return qrFormDao.changeStatus(qrformId, status);
    }
}
