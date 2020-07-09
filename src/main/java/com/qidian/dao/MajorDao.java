package com.qidian.dao;

import com.qidian.domain.Academic;
import com.qidian.domain.Major;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorDao {

    @Select("select * from major")
    public List<Major>  findAll();

    @Select("select * from major where academicId=#{academicId}")
    public List<Major> findByAcademicId(int academicId);

    @Select("select * from major where majorId=#{majorId}")
    public Major findByMajorId(int majorId);

    @Insert("insert into major (majorName,academicId,academicName,status) values(#{majorName},#{academicId},#{academicName},#{status})")
    public boolean save(Major major);

    /**
     * 更新专业信息
     * @param major
     */
    @Update("update major set majorName=#{majorName},academicId=#{academicId},academicName=#{academicName},status=#{status} where majorId=#{majorId} ")
    public boolean update(Major major);

    /**
     * 根据majorId删除专业
     * @param majorId
     */
    @Delete("delete from major where majorId=#{majorId}")
    public boolean delete(@Param("majorId") int majorId);

    @Delete("<script> delete from major where majorId in"+
            "<foreach collection=\"list\" item=\"majorId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{majorId}"+
            "</foreach>"+
            "</script>")
    public int deleteMajorList(List<String> majorIdList);
    /**
     * @Description:  根据taskId禁用任务
     * @Date:
     * @Author:
     */

    @Update("update major set status=#{status} where majorId=#{majorId}")
    public boolean changeStatus(@Param("majorId")int majorId,@Param("status")String status);

  

}
