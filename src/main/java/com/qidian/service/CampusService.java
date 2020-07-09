package com.qidian.service;

import com.qidian.domain.Campus;

import java.util.List;

public interface CampusService {
    List<Campus> findAll();
    List<Campus> findAllBySchoolId(int schoolId);
}
