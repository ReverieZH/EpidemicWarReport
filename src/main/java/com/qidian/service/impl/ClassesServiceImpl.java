package com.qidian.service.impl;

import com.qidian.dao.ClassesDao;
import com.qidian.domain.Classes;
import com.qidian.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("classesService")
@Transactional
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Override
    public List<Classes> findAll() {
        return classesDao.findAll();
    }

    @Override
    public List<Classes> findAllByMajor(int majorId) {
        return classesDao.findAllByMajor(majorId);
    }

    @Override
    public Classes findByClassesId(int classesId) {
        return classesDao.findByClassesId(classesId);
    }

    @Override
    public boolean save(Classes classes) {
        return classesDao.save(classes);
    }

    @Override
    public boolean update(Classes classes) {
        return classesDao.update(classes);
    }

    @Override
    public boolean delete(int classesId) {
        return classesDao.delete(classesId);
    }

    @Override
    public int deleteClassesList(List<String> classesIdList) {
        return classesDao.deleteClassesList(classesIdList);
    }

    @Override
    public boolean changeStatus(int classesId, String status) {
        return classesDao.changeStatus(classesId,status);
    }
}
