package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.PageBean;
import com.qidian.domain.Question;
import com.qidian.domain.Questionnaire;
import com.qidian.domain.Task;
import com.qidian.service.QuestionnaireService;
import com.qidian.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/questionnarie")
public class QuestionnarieController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @RequestMapping("getQuestionnaires.do")
    @ResponseBody
    public List<Questionnaire> getQuestionnaires(@RequestParam int taskId){
        return questionnaireService.findAllByTaskId(taskId);
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Questionnaire> questionnaireList = questionnaireService.findAll();
        mv.addObject("questionnaireList",questionnaireList);
        mv.setViewName("questionnaire-list");
        return mv;
    }



    @RequestMapping("/main.do")
    public String findByTaskId(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit,@RequestParam int taskId){
        System.out.println("FORMMAIN******************************************");

        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);
        System.out.println("param:"+taskId+" "+startPage+" "+pageSize);
        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("questionnaireid ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Questionnaire> questionnaires = questionnaireService.findAllByTaskId(taskId);

        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        /*long total = page.getTotal();

        // 一下是layui的分页要求的信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("data",questionnaires);
        map.put("count",total);
*/
        long total = pager.getTotal();
        System.out.println("questionnaireTotal------>");
        if(total!=0){
            PageBean<Questionnaire> pageBean=new PageBean<Questionnaire>(startPage,pageSize, (int) total);
            pageBean.setList(questionnaires);
            request.setAttribute("pageBean",pageBean);
        }
        request.setAttribute("taskId",taskId);
        return  "/Questionnarielist.jsp";
    }

    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public String changeStatus(HttpServletRequest request,@RequestParam int questionnaireId ,@RequestParam String status){
        boolean issuccess =false;
        issuccess=questionnaireService.changeStatus(questionnaireId,status);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getaddQuestionnaire.do")
    public String getaddform(HttpServletRequest request,@RequestParam int taskId){
        request.setAttribute("taskId",taskId);
        return "/addQuestionnaire.jsp";
    }
    @RequestMapping("addQuestionnarie.do")
    @ResponseBody
    public String saveQuestionnarie(HttpServletResponse response, Questionnaire questionnaire,@RequestParam String date) throws IOException {
        boolean issuccess=false;
        questionnaire.setDatetime(new Timestamp(new Date().getTime()));
        questionnaire.setStatus("1");
        System.out.println(questionnaire);
        issuccess = questionnaireService.save(questionnaire);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getmodifyQuestionnaire.do")
    public String getmodifyForm(HttpServletRequest request, HttpServletResponse response, @RequestParam()int questionnaireId){
        Questionnaire questionnaire=questionnaireService.findByQuestionnarieId(questionnaireId);
        request.setAttribute("questionnaire",questionnaire);
        return "/modifyQuestionnaire.jsp";
    }

    @RequestMapping("updateQuestionnarie.do")
    @ResponseBody
    public String updateQuestionnarie(HttpServletRequest request, HttpServletResponse response, Questionnaire questionnaire) throws IOException {
        boolean issuccess =false;
        System.out.println("Questionnarieupdate----"+questionnaire);
        issuccess=questionnaireService.update(questionnaire);
        return String.valueOf(issuccess);
    }

    @RequestMapping("deleteQuestionnaire.do")
    @ResponseBody
    public String deleteQuestionnarie(HttpServletResponse response, @RequestParam("questionnaireId")List<String> questionnaireIdList) throws IOException {
         boolean issuccess=false;
        int total = questionnaireService.deleteList(questionnaireIdList);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(HttpServletResponse response, @RequestParam int questionnaireId) throws IOException {
        boolean issuccess=false;
        issuccess=questionnaireService.delete(questionnaireId);
        return String.valueOf(issuccess);
    }


    @RequestMapping("getLink.do")
    public String getLink(HttpServletRequest request,int questionnaireId){
        String link="";
        System.out.println(request.getContextPath());
        link=request.getContextPath()+"/question/getQuestionnaire.do?questionnaireId="+questionnaireId;
        System.out.println("link-----:"+link);
        request.setAttribute("link",link);
        return "/link.jsp";
    }

}
