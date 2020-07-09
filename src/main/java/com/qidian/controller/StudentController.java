package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.*;
import com.qidian.service.ApplyService;
import com.qidian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {



    @Autowired
    private StudentService studentService;



    @RequestMapping("main.do")
    public String studentList(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("sno ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Student> students = studentService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Student> pageBean=new PageBean<Student>(startPage,pageSize, (int) total);
            pageBean.setList(students);
            request.setAttribute("pageBean",pageBean);
        }
        /*// 一下是layui的分页要求的信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("data",tasks);
        map.put("count",total);
*/
        /*   request.setAttribute("taskmap",map);*/
        return  "/studentlist.jsp";
    }

    @RequestMapping("getStudent.do")
    public String getStudent(HttpServletRequest request,@RequestParam String sno){
        Student student=studentService.findBySno(sno);
        request.setAttribute("student",student);
        return "student.jsp";
    }

    @RequestMapping("getaddStudent.do")
    public String getaddStudent(){
        return "addStudent.jsp";
    }

    @RequestMapping("addStudent.do")
    @ResponseBody
    public String addStudent(Student student){
        boolean issuccess=false;
        student.setStatus("1");
        student.setSchoolName("陕西科技大学");
        System.out.println(student);
        issuccess=studentService.save(student);
        return String.valueOf(issuccess);

    }

    @RequestMapping("getmodifyStudent.do")
    public String getmodifyStudent(HttpServletRequest request,@RequestParam String sno){
        Student student=studentService.findBySno(sno);
        request.setAttribute("student",student);
        return "modifyStudent.jsp";
    }

    @RequestMapping("modifyStudent.do")
    @ResponseBody
    public String modifyStudent(Student student){
        boolean issuccess=false;
        student.setSchoolName("陕西科技大学");
        System.out.println(student);
        issuccess=studentService.update(student);
        return String.valueOf(issuccess);
    }

    @RequestMapping("deleteStudent.do")
    @ResponseBody
    public String deleteMajors(@RequestParam("sno")List<String> snoList){
        boolean issuccess=false;
        int total=studentService.deleteStudentList(snoList);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }


    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(@RequestParam("sno")String sno){
        boolean issuccess=false;
        issuccess=studentService.delete(sno);
        return String.valueOf(issuccess);
    }

    @RequestMapping("changeStatus.do")
    @ResponseBody
    public String changeStatus(@RequestParam("sno")String sno,@RequestParam("status")String status){
        boolean issuccess=false;
        issuccess=studentService.changeStatus(sno,status);
        return String.valueOf(issuccess);
    }

}
