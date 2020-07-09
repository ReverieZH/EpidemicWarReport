package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.PageBean;
import com.qidian.domain.Question;
import com.qidian.domain.Questionnaire;
import com.qidian.domain.Task;
import com.qidian.service.AnsewrService;
import com.qidian.service.QuestionService;
import com.qidian.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnsewrService ansewrService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Question> questionList = questionService.findAll();
        mv.addObject("questionn",questionList);
        mv.setViewName("question-list");
        return mv;
    }

    @RequestMapping("/main.do")
    public String findByQuestionnaireID( HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit, @RequestParam int questionnaireId){
        System.out.println("questionMain--------------");

        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("questionId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Question> questions = questionService.findAllByQuestionnaireId(questionnaireId);
        System.out.println("questions page:"+questions);
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Question> pageBean=new PageBean<Question>(startPage,pageSize, (int) total);
            pageBean.setList(questions);
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
        request.setAttribute("questionnaireId",questionnaireId);
        return  "/questionlist.jsp";
    }

    @RequestMapping("/list")
    public String getQuestions(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "1") int questionnarieId){
        List<Question> questions = questionService.findAllByQuestionnaireId(questionnarieId);
        request.setAttribute("questions",questions);
        return "questionsmain.jsp";
    }

    @RequestMapping("getaddQuestion.do")
    public String getaddform(HttpServletRequest request,@RequestParam int questionnaireId){
        request.setAttribute("questionnaireId",questionnaireId);
        return "/addQuestion.jsp";
    }


    @RequestMapping("addQuestion.do")
    @ResponseBody
    public String saveQuestion(HttpServletResponse response,Question question) throws IOException {
            boolean issuccess=false;
            System.out.println("addQuestion.do^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(question);
            issuccess=questionService.save(question);
            return String.valueOf(issuccess);
    }

    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public String changeStatus(HttpServletRequest request,@RequestParam int questionId ,@RequestParam String status){
        boolean issuccess =false;
        issuccess=questionService.changeStatus(questionId,status);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getmodifyQuestion.do")
    public String getmodifyQuestion(HttpServletRequest request, HttpServletResponse response, @RequestParam int questionId) throws IOException {
        Question question = questionService.findByQuestionId(questionId);
        request.setAttribute("question",question);
        System.out.println("question^^^^^^^^"+question);
        return "/modifyQuestion.jsp";

    }

    @RequestMapping("updateQuestion.do")
    @ResponseBody
    public String updateQuestion(HttpServletRequest request, Question question){
        System.out.println("updateQuestion^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(question);
        boolean issuccess=false;
        issuccess=questionService.update(question);
        return String.valueOf(issuccess);
    }

    @RequestMapping("deleteQuestion.do")
    @ResponseBody
    public String deleteQuestion(HttpServletResponse response, @RequestParam("questionId")List<String> questionIdList) throws IOException {
            boolean issuccess=false;
            int total=questionService.deleteList(questionIdList);
            if(total>0){
                issuccess=true;
            }
            return String.valueOf(issuccess);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String deleteOneQuestion(HttpServletResponse response, @RequestParam int questionId){
        boolean issuccess=false;
        issuccess=questionService.delete(questionId);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getQuestionnaire.do")
    public void getQuestionnaire(HttpServletRequest request,HttpServletResponse response,@RequestParam int questionnaireId) throws ServletException, IOException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=new Date();
        Date endDate=new Date(startDate.getTime()+86400000);
        String start = dateFormat.format(startDate);
        String end = dateFormat.format(endDate);
        int count = ansewrService.selectCountBySno("201706060325", start, end);
        if(count>0){
            Common.alertAndBack(response,"今日已填写");
        }else {
            List<Question> questions = questionService.findAllByQuestionnaireId(questionnaireId);
            System.out.println("getQuestionnaire----------:" + questions);
            System.out.println(questions.get(0).getStem() + "  ---- " + questions.get(0).getOptionlist().size());
            System.out.println(questions.get(0).getOptionlist());
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(questionnaireId);
            questionnaire.setQuestionsList(questions);
            questionnaire.setQueName("每日打卡");
            request.setAttribute("questionnaire", questionnaire);
            request.getRequestDispatcher("/questionnaire.jsp").forward(request, response);
        }
    }
}
