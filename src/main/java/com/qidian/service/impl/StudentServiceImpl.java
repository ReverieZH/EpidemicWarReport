package com.qidian.service.impl;

import com.qidian.dao.StudentDao;
import com.qidian.domain.Student;
import com.qidian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student login(String sno, String password) {
        return studentDao.login(sno,password);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findBySno(String sno) {
        return studentDao.findBySno(sno);
    }

    @Override
    public boolean save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);

    }

    @Override
    public boolean delete(String sno) {
        return studentDao.delete(sno);

    }

    @Override
    public int deleteStudentList(List<String> snoList) {
        return studentDao.deleteStudentList(snoList);
    }

    @Override
    public boolean changeStatus(String sno, String status) {
        return studentDao.changeStatus(sno,status);
    }
}
