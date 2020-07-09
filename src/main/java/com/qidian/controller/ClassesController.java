package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Classes;
import com.qidian.domain.Major;
import com.qidian.domain.PageBean;
import com.qidian.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping("/getClasses.do")
    @ResponseBody
    public List<Classes> getClasses(@RequestParam int majorId){
        return  classesService.findAllByMajor(majorId);
    }

    @RequestMapping("main.do")
    public String classesMain(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("majorId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Classes> Classes = classesService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Classes> pageBean=new PageBean<Classes>(startPage,pageSize, (int) total);
            pageBean.setList(Classes);
            request.setAttribute("pageBean",pageBean);
        }

        return "/classeslist.jsp";
    }

    @RequestMapping("getaddClasses.do")
    public String getaddMajor(){
        return "addClasses.jsp";
    }

    @RequestMapping("addClasses.do")
    @ResponseBody
    public String addMajor(Classes classes){
        boolean issuccess=false;
        classes.setStatus("1");
        issuccess=classesService.save(classes);
        return String.valueOf(issuccess);
    }

    @RequestMapping("deleteClasses.do")
    @ResponseBody
    public String deleteMajors(@RequestParam("classesId")List<String> classesIdList){
        boolean issuccess=false;
        int total=classesService.deleteClassesList(classesIdList);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(@RequestParam("classesId")int classesId){
        boolean issuccess=false;
        issuccess=classesService.delete(classesId);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getmodifyClasses.do")
    public String getmodifyMajor(HttpServletRequest request,@RequestParam("classesId")int classesId){
        System.out.println("classId；"+classesId);
        Classes classes = classesService.findByClassesId(classesId);
        System.out.println(classes);
        request.setAttribute("classes",classes);
        return "/modifyClasses.jsp";
    }

    @RequestMapping("updateClasses.do")
    @ResponseBody
    public String updateMajor(Classes classes){
        System.out.println("classes:    "+classes);
        boolean issuccess=false;
        issuccess = classesService.update(classes);
        return String.valueOf(issuccess);
    }


    @RequestMapping("changeStatus.do")
    @ResponseBody
    public String changeStatus(@RequestParam("classesId")int classesId,@RequestParam("status")String status){
        boolean issuccess=false;
        issuccess=classesService.changeStatus(classesId,status);
        return String.valueOf(issuccess);
    }

}
