package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.PageBean;
import com.qidian.domain.Questionnaire;
import com.qidian.domain.Student;
import com.qidian.domain.Task;
import com.qidian.service.AnsewrService;
import com.qidian.service.QuestionnaireService;
import com.qidian.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AnsewrService ansewrService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @RequestMapping("/main.do")
    public String findByOperator(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        String username= String.valueOf(request.getSession().getAttribute("username"));
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("taskId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Task> tasks = taskService.findAll();
        System.out.println("tasks page:"+tasks);
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Task> pageBean=new PageBean<Task>(startPage,pageSize, (int) total);
            pageBean.setList(tasks);
            request.setAttribute("pageBean",pageBean);
        }

        return  "/datalist.jsp";
    }

    @RequestMapping("/account.do")
    public String account(HttpServletRequest request, @RequestParam int taskId, @RequestParam(defaultValue = "0") int order,@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit,  @RequestParam Map<String,String> map){

        List<Questionnaire> questionnaires = questionnaireService.findAllAndQuestion(taskId);

        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String answerDate;
        System.out.println("answerDate:::"+map.get("answerDate"));

        if(map.get("answerDate")!=null){
            answerDate=map.get("answerDate");
        }else{
            Date date=new Date();
            answerDate= dateFormat.format(date);
        }



        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Student> students=new ArrayList<>();
        if(map.get("questionnaireId")!=null){
            int questionnaireId= Integer.parseInt(map.get("questionnaireId"));
            System.out.println("切表");
            students= ansewrService.group(map.get("academic"),map.get("majors"),map.get("className"),map.get("name"),map.get("type"),questionnaireId,answerDate);
        }else{
            students= ansewrService.group(map.get("academic"),map.get("majors"),map.get("className"),map.get("name"),map.get("type"),questionnaires.get(0).getQuestionnaireId(),answerDate);
        }
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Student> pageBean=new PageBean<Student>(startPage,pageSize, (int) total);
            pageBean.setList(students);
            request.setAttribute("pageBean",pageBean);
        }
        request.setAttribute("questionnaires",questionnaires);
        request.setAttribute("taskId",taskId);
        request.setAttribute("order",order);

        String condition=map.get("condition");
        System.out.println(condition);
        if(map.get("condition")==null){   //已填还是没填还是全部
        }else {
            if(!condition.equals("2")){
                System.out.println("condition:-------"+map.get("condition"));
                request.setAttribute("condition",condition);
            }
        }

        return "/account.jsp";
    }


    @RequestMapping("/qraccount.do")
    public String account(HttpServletRequest request, @RequestParam int questionnaireId,@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit,  @RequestParam Map<String,String> map){

       Questionnaire questionnaire=questionnaireService.findAndQuestion(questionnaireId);
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String answerDate;

        if(map.get("answerDate")!=null){
            answerDate=map.get("answerDate");
        }else{
            Date date=new Date();
            answerDate= dateFormat.format(date);
        }


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了

        List<Student> students= ansewrService.group(map.get("academic"),map.get("majors"),map.get("className"),map.get("name"),map.get("type"),questionnaireId,answerDate);

        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Student> pageBean=new PageBean<Student>(startPage,pageSize, (int) total);
            pageBean.setList(students);
            request.setAttribute("pageBean",pageBean);
        }
        request.setAttribute("questionnaire",questionnaire);

        return "/QrFormAccount.jsp";
    }
}
