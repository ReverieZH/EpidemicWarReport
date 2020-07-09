package com.qidian.service;

import com.qidian.domain.Student;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface StudentService {

    public Student login(String sno,String password);

    public List<Student> findAll();

    public Student findBySno(String sno);

    public boolean save(Student student);


    public boolean update(Student student);


    public boolean delete(String sno);

    public int deleteStudentList(List<String> snoList);

    public boolean changeStatus(String sno, String status);

}
