package com.qidian.dao;

import com.qidian.domain.Academic;
import com.qidian.domain.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicDao {

    @Select("select * from academic")
    public  List<Academic> findAll();

    @Select("select * from academic where academicId=#{academicId}")
    public Academic findByAcademicId(int academicId);


    @Select("select * from academic where schoolId=#{schoolId}")
    public List<Academic> findAllBySchool(@Param("schoolId") int schoolId);


    @Select("select * from acadmic where schoolId=#{schoolId} and campusId=#{campusId}")
    public List<Academic> findAllBySchoolAndCampus(@Param("schoolId")int schoolId,@Param("campusId")int campusId);

    @Select("select academicName from academic where schoolId=#{schoolId} and campusId=#{campusId}")
    public List<String> findAllName(@Param("schoolId")int schoolId,@Param("campusId")int campusId);

    @Insert("insert into academic (academicName,status) values(#{academicName},#{status})")
    public boolean save(Academic academic);

    /**
     * 更新学院信息
     * @param academic
     */
    @Update("update academic set academicName=#{academicName},status=#{status} where academicId=#{academicId} ")
    public boolean update(Academic academic);

    /**
     * 根据academicId删除学院
     * @param academicId
     */
    @Delete("delete from academic where academicId=#{academicId}")
    public boolean delete(@Param("academicId") int academicId);

    @Delete("<script> delete from academic where academicId in"+
            "<foreach collection=\"list\" item=\"academicId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{academicId}"+
            "</foreach>"+
            "</script>")
    public int deleteAcademicList(List<String> academicIdList);
    /**
     * @Description:  根据taskId禁用任务
     * @Date:
     * @Author:
     */

    @Update("update academic set status=#{status} where academicId=#{academicId}")
    public boolean changeStatus(@Param("academicId")int academicId,@Param("status")String status);




}
