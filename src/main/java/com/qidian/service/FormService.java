package com.qidian.service;

import com.qidian.domain.Form;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FormService {

    public List<Form> findAll();


    public List<Form>  findAllByApplyName(String applyName);

    public Form findByFormId(int formId);


    public boolean save(Form form);


    public boolean update(Form form);

    public boolean delete( int formId);


    public int deleteFormList(List<String> formIdList);


    public boolean changeStatus(int formId,String status);
}
