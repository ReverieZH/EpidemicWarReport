package com.qidian.service;

import com.qidian.domain.Academic;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AcademicService {

    public List<Academic> findAll();

    public Academic findByAcademicId(int academicId);

    public List<Academic> findAllBySchool( int schoolId);


    public List<Academic> findAllBySchoolAndCampus(int schoolId,int campusId);

    public List<String> findAllName(int schoolId,int campusId);


    public boolean save(Academic academic);


    public boolean update(Academic academic);


    public boolean delete( int academicId);


    public int deleteAcademicList(List<String> academicIdList);

    public boolean changeStatus(int academicId,String status);
}
