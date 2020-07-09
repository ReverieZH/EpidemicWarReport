package com.qidian.service.impl;

import com.qidian.dao.CampusDao;
import com.qidian.domain.Campus;
import com.qidian.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("campusService")
@Transactional
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusDao campusDao;

    @Override
    public List<Campus> findAll() {
        return campusDao.findAll();
    }

    @Override
    public List<Campus> findAllBySchoolId(int schoolId) {
        return campusDao.findAllBySchoolId(schoolId);
    }
}
