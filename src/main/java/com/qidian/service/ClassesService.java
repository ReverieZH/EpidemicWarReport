package com.qidian.service;

import com.qidian.domain.Classes;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClassesService {

    List<Classes> findAll();


    List<Classes> findAllByMajor(int majorId);

    public Classes findByClassesId(int classesId);

    public boolean save(Classes classes);


    public boolean update(Classes classes);

    public boolean delete(int classesId);

    public int deleteClassesList(List<String> classesIdList);


    public boolean changeStatus(int classesId,String status);
}
