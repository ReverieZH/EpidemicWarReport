package com.qidian.service.impl;

import com.qidian.dao.AcademicDao;
import com.qidian.domain.Academic;
import com.qidian.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("academicService")
@Transactional
public class AcademicServiceImpl implements AcademicService {

    @Autowired
    private AcademicDao academicDao;


    @Override
    public List<Academic> findAll() {
        return academicDao.findAll();
    }

    @Override
    public Academic findByAcademicId(int academicId) {
        return academicDao.findByAcademicId(academicId);
    }

    @Override
    public List<Academic> findAllBySchool(int schoolId) {
        return academicDao.findAllBySchool(schoolId);
    }

    @Override
    public List<Academic> findAllBySchoolAndCampus(int schoolId, int campusId) {
        return academicDao.findAllBySchoolAndCampus(schoolId,campusId);
    }

    @Override
    public List<String> findAllName(int schoolId, int campusId) {
        return academicDao.findAllName(schoolId,campusId);
    }

    @Override
    public boolean save(Academic academic) {
        return academicDao.save(academic);
    }

    @Override
    public boolean update(Academic academic) {
        return academicDao.update(academic);
    }

    @Override
    public boolean delete(int academicId) {
        return academicDao.delete(academicId);
    }

    @Override
    public int deleteAcademicList(List<String> academicIdList) {
        return academicDao.deleteAcademicList(academicIdList);
    }

    @Override
    public boolean changeStatus(int academicId, String status) {
        return academicDao.changeStatus(academicId,status);
    }
}
