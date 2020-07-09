package com.qidian.dao;


import com.qidian.domain.Classes;
import com.qidian.domain.Major;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {

    @Select("select * from classes")
    List<Classes> findAll();


    @Select("select * from classes where majorId=#{majorId}")
    List<Classes> findAllByMajor(@Param("majorId")int majorId);

    @Select("select * from classes where classesId=#{classesId}")
    public Classes findByClassesId(int classesId);

    @Insert("insert into classes (classesName,majorId,majorName,status,academicId) values(#{classesName},#{majorId},#{majorName},#{status},#{academicId})")
    public boolean save(Classes classes);

    /**
     * 更新班级信息
     * @param classes
     */
    @Update("update classes set classesName=#{classesName},majorId=#{majorId},majorName=#{majorName},status=#{status},academicId=#{academicId} where classesId=#{classesId} ")
    public boolean update(Classes classes);

    /**
     * 根据classesId删除班级
     * @param classesId
     */
    @Delete("delete from classes where classesId=#{classesId}")
    public boolean delete(@Param("classesId") int classesId);

    @Delete("<script> delete from classes where classesId in"+
            "<foreach collection=\"list\" item=\"classesId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{classesId}"+
            "</foreach>"+
            "</script>")
    public int deleteClassesList(List<String> classesIdList);
    /**
     * @Description:  根据taskId禁用任务
     * @Date:
     * @Author:
     */

    @Update("update classes set status=#{status} where classesId=#{majorId}")
    public boolean changeStatus(@Param("classesId")int classesId,@Param("status")String status);


}
