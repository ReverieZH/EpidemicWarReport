package com.qidian.service;

import com.qidian.domain.School;

import java.util.List;

public interface SchoolService {

    /**
     * 查询所有
     * @return
     */
    public List<School> findAll();

    /**
     * 根据schoolId查询学校信息
     * @param schoolId
     * @return
     */
    public School findBySchoolId(String schoolId);

    /**
     * 新建学校信息
     * @param school
     */
    public void save(School school);

    /**
     * 更新学校信息
     * @param school
     */
    public void update(School school);


    public void delete(String schoolId);
}
