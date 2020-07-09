package com.qidian.dao;

import com.qidian.domain.Campus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CampusDao {

  @Select("select * from campus")
    List<Campus> findAll();

  @Select("select * from campus where schoolId=#{schoolId}")
    List<Campus> findAllBySchoolId(@Param("schoolId") int schoolId);

}
