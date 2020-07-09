package com.qidian.dao;


import com.qidian.domain.Apply;
import com.qidian.domain.Form;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FormDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from form")
    public List<Form> findAll();

    /**
     * @Description: 根据operatorId查询所有的task
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from form where applyName=#{0}")
    public List<Form>  findAllByApplyName(String applyName);

    /**
     * 根据applyId查询任务信息
     * @param formId
     * @return
     */
    @Select("select * from form where formId=#{0}")
    public Form findByFormId(int formId);

    /**
     * 新建任务
     * @param form
     */
    @Insert("insert into form (formName,status,applyName,link) value (#{formName},#{status},#{applyName},#{link})")
    public boolean save(Form form);

    /**
     * 更新任务信息
     * @param form
     */
    @Update("update form set formName=#{formName},applyName=#{applyName},link=#{link},status=#{status} where formId=#{formId} ")
    public boolean update(Form form);

    /**
     * 根据taskId删除任务
     * @param formId
     */
    @Delete("delete from form where formId=#{formId}")
    public boolean delete(@Param("formId") int formId);

    @Delete("<script> delete from form where formId in"+
            "<foreach collection=\"list\" item=\"formId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{formId}"+
            "</foreach>"+
            "</script>")
    public int deleteFormList(List<String> formIdList);
    /**
     * @Description:  根据taskId禁用任务
     * @Date:
     * @Author:
     */

    @Update("update form set status=#{status} where formId=#{formId}")
    public boolean changeStatus(@Param("formId")int formId,@Param("status")String status);




}
