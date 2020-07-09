package com.qidian.dao;


import com.qidian.domain.Apply;
import com.qidian.domain.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from apply")
    public List<Apply> findAll();


    @Select("select applyName from apply")
    public List<String> findAllName();


    /**
     * @Description: 根据operatorId查询所有的task
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from apply where username=#{0}")
    public List<Apply>  findAllByOperator(String username);

    /**
     * 根据applyId查询任务信息
     * @param applyId
     * @return
     */
    @Select("select * from apply where applyId=#{0}")
    public Apply findByApplyId(int applyId);

    /**
     * 新建任务
     * @param apply
     */
    @Insert("insert into apply (applyName,status,username) value (#{applyName},#{status},#{username})")
    public boolean save(Apply apply);

    /**
     * 更新任务信息
     * @param apply
     */
    @Update("update apply set applyName=#{applyName},status=#{status} where applyId=#{applyId} ")
    public boolean update(Apply apply);

    /**
     * 根据taskId删除任务
     * @param applyId
     */
    @Delete("delete from apply where applyId=#{applyId}")
    public boolean delete(@Param("applyId") int applyId);

    @Delete("<script> delete from apply where applyId in"+
            "<foreach collection=\"list\" item=\"applyId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{applyId}"+
            "</foreach>"+
            "</script>")
    public int deleteApplyList(List<String> applyIdList);
    /**
     * @Description:  根据taskId禁用任务
     * @Date:
     * @Author:
     */

    @Update("update apply set status=#{status} where applyId=#{applyId}")
    public boolean changeStatus(@Param("applyId")int applyId,@Param("status")String status);

    @Results(value = {
            @Result(property = "applyName",column = "applyName"),
            @Result(property = "formList",column = "applyName",many = @Many(select = "com.qidian.dao.FormDao.findAllByApplyName"))   })
    @Select("select * from apply")
    public List<Apply>  group();
}
