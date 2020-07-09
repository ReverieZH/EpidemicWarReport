package com.qidian.service.impl;

import com.qidian.dao.MajorDao;
import com.qidian.domain.Major;
import com.qidian.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("majorService")
@Transactional
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorDao majorDao;

    @Override
    public List<Major> findAll() {
        return majorDao.findAll();
    }

    @Override
    public List<Major> findByAcademicId(int academicId) {
        return majorDao.findByAcademicId(academicId);
    }

    @Override
    public Major findByMajorId(int majorId) {
        return majorDao.findByMajorId(majorId);
    }

    @Override
    public boolean save(Major major) {
        return majorDao.save(major);
    }

    @Override
    public boolean update(Major major) {
        return majorDao.update(major);
    }

    @Override
    public boolean delete(int majorId) {
        return majorDao.delete(majorId);
    }

    @Override
    public int deleteMajorList(List<String> majorIdList) {
        return majorDao.deleteMajorList(majorIdList);
    }

    @Override
    public boolean changeStatus(int majorId, String status) {
        return majorDao.changeStatus(majorId,status);
    }
}
