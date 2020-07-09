package com.qidian.service;

import com.qidian.domain.Apply;
import com.qidian.domain.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ApplyService {

    public List<Apply> findAll();

    public List<String> findAllName();

    public List<Apply>  findAllByOperator(String username);


    public Apply findByApplyId(int applyId);


    public boolean save(Apply apply);


    public boolean update(Apply apply);


    public boolean delete(@Param("applyId") int applyId);


    public int deleteApplyList(List<String> applyIdList);

    public boolean changeStatus(@Param("applyId")int applyId,@Param("status")String status);

    public List<Apply> group();
}
