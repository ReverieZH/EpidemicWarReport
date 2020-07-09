package com.qidian.dao;

import com.qidian.domain.School;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from schools")
    public List<School> findAll();

    /**
     * 根据schoolId查询学校信息
     * @param schoolId
     * @return
     */
    @Select("select * from schools where schoolId=#{schoolId}")
    public School findBySchoolId(String schoolId);

    /**
     * 新建学校信息
     * @param school
     */
    @Insert("insert into schools (schoolName) value (#{schoolName})")
    public void save(School school);

    /**
     * 更新学校信息
     * @param school
     */
    @Update("update schools set schoolName=#{schoolName} where schoolId=#{schoolId}")
    public void update(School school);

    /**
     * 根据schoolId删除学校信息
     * @param schoolId
     */
    @Delete("delete from schools where schoolId=#{schoolId}")
    public void delete(String schoolId);
}
