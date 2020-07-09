package com.qidian.service;

import com.qidian.domain.Academic;
import com.qidian.domain.Major;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MajorService {

    public List<Major> findAll();

    public List<Major> findByAcademicId(int academicId);

    public Major findByMajorId(int majorId);

    public boolean save(Major major);

    public boolean update(Major major);


    public boolean delete( int majorId);


    public int deleteMajorList(List<String> majorIdList);

    public boolean changeStatus(int majorId,String status);
}
