package com.qidian.service.impl;

import com.qidian.dao.SchoolDao;
import com.qidian.domain.School;
import com.qidian.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("schoolService")
@Transactional
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<School> findAll() {
        return schoolDao.findAll();
    }

    @Override
    public School findBySchoolId(String schoolId) {
        return schoolDao.findBySchoolId(schoolId);
    }

    @Override
    public void save(School school) {
        schoolDao.save(school);
    }

    @Override
    public void update(School school) {
        schoolDao.update(school);
    }

    @Override
    public void delete(String schoolId) {
        schoolDao.delete(schoolId);
    }
}
