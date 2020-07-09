package com.qidian.dao;


import com.qidian.domain.School;
import com.qidian.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    @Select("select sno,password,name from students where sno=#{sno} and password=#{password} ")
    public Student login(@Param("sno") String sno,@Param("password") String password);

    @Select("select * from students")
    public List<Student> findAll();
//    sno,name,password,sex,grade,academicName,majorName,classesName,dormitory,origin,address,phone

    @Select("select * from students where sno=#{sno}")
    public Student findBySno(@Param("sno") String sno);

    @Insert("insert into students (sno,name,password,sex,grade,type,academicName,majorName,classesName,dormitory,origin,address,phone,schoolName,status) value (#{sno},#{name},#{password},#{sex},#{grade},#{type},#{academicName},#{majorName},#{classesName},#{dormitory},#{origin},#{address},#{phone},#{schoolName},#{status})")
    public boolean save(Student student);

    /**
     * 更新学生信息
     * @param student
     */
    @Update("update students set sno=#{sno},name=#{name},password=#{password},sex=#{sex},grade=#{grade},academicName=#{academicName},majorName=#{majorName},classesName=#{classesName},dormitory=#{dormitory},origin=#{origin},address=#{address},phone=#{phone} where sno=#{sno}")
    public boolean update(Student student);

    /**
     * 根据sno删除学生信息
     * @param sno
     */
    @Delete("delete from students where sno=#{sno}")
    public boolean delete(String sno);

    @Delete("<script> delete from students where sno in"+
            "<foreach collection=\"list\" item=\"sno\" open=\"(\" close=\")\" separator=\",\">"+
            "#{sno}"+
            "</foreach>"+
            "</script>")
    public int deleteStudentList(List<String> snoList);


    @Update("update students set status=#{status} where sno=#{sno}")
    public boolean changeStatus(@Param("sno")String sno,@Param("status")String status);
}
