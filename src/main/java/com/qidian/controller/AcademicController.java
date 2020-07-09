package com.qidian.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.qidian.domain.Academic;
import com.qidian.domain.Campus;
import com.qidian.domain.PageBean;
import com.qidian.domain.Task;
import com.qidian.service.AcademicService;
import com.qidian.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/academic")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @RequestMapping(value = "getAcademics.do")
    @ResponseBody
    public List<Academic> getAcademics(HttpServletRequest request){
        return academicService.findAll();
    }

    @RequestMapping("main.do")
    public String findByOperator(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        System.out.println("taskmain页面-------"+page+"  "+limit);
        String username= String.valueOf(request.getSession().getAttribute("username"));
        System.out.println("taskmain----->"+username);
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("academicId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Academic> academics = academicService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Academic> pageBean=new PageBean<Academic>(startPage,pageSize, (int) total);
            pageBean.setList(academics);
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
        return  "/academiclist.jsp";
    }

    @RequestMapping("getaddAcademic")
    public String getaddAcademic(){
        return "/addAcademic.jsp";
    }

    @RequestMapping("addAcademic.do")
    @ResponseBody
    public String addAcademic(HttpServletRequest request,Academic academic){
        boolean issuccess =false;
        academic.setStatus("1");
        issuccess=academicService.save(academic);
        return String.valueOf(issuccess);
    }


    @RequestMapping("/deleteAcademic.do")
    @ResponseBody
    public String deleteAcademic(HttpServletRequest request, HttpServletResponse response, @RequestParam("academicId")List<String> academicIdList, @RequestParam(defaultValue = "1") String page) throws IOException {
        boolean issuccess=false;
        int total=academicService.deleteAcademicList(academicIdList);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(@RequestParam("academicId")int academicId){
        boolean issuccess=false;
        issuccess=academicService.delete(academicId);
        return String.valueOf(issuccess);
    }

    @RequestMapping("changeStatus.do")
    @ResponseBody
    public String changeStatus(@RequestParam("academicId")int academicId,@RequestParam("status")String status){
        boolean issuccess=false;
        issuccess=academicService.changeStatus(academicId,status);
        return String.valueOf(issuccess);
    }

    @RequestMapping("/getmodifyAcademic.do")
    public String getmodifyTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int academicId){
        Academic academic = academicService.findByAcademicId(academicId);
        request.setAttribute("academic",academic);
        return "/modifyAcademic.jsp";
    }

    @RequestMapping("updateAcademic.do")
    @ResponseBody
    public String updateAcademic(Academic academic){
        boolean issuccess=false;
        issuccess=academicService.update(academic);
        return String.valueOf(issuccess);
    }
}
