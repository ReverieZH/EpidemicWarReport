package com.qidian.controller;

import com.qidian.domain.Apply;
import com.qidian.domain.Form;
import com.qidian.domain.Operator;
import com.qidian.domain.Student;
import com.qidian.service.ApplyService;
import com.qidian.service.OperatorService;
import com.qidian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("login.do")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response){
        System.out.println(username+" "+password);

        boolean issuccess=false;
        PrintWriter out = null;


        try {
            out=response.getWriter();
            Operator operator = operatorService.login(username,password);
            System.out.println("获取的operator---->"+operator);
            if(operator!= null){
                issuccess=true;
                request.getSession().setAttribute("username",operator.getUsername());
                request.getSession().setAttribute("role",operator.getRole());
                String succ=String.valueOf(issuccess);
                out.write(String.valueOf(issuccess));
                out.flush();
                out.close();
//            mv.setViewName("redirect:/task/main.do");
            }else {
                issuccess=false;
                System.out.println("is:"+issuccess);
                out.write(String.valueOf(issuccess));
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*@RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, Operator user){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+" "+password);
        System.out.println(user);
        // response.setContentType("text/html;charset=utf-8");
        Operator operator=operatorService.login(username,password);
            if(operator!=null) {
                request.getSession().setAttribute("username", user.getUsername());
                return "redirect:/task/main.do";
            }else {
                return "login";
            }
    }*/
    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request){
        if(request.getSession().getAttribute("operator") != null){
            //清除session
            request.getSession().removeAttribute("operator");
        }
        //重定向到登录页面
        return "redirect:/login.html";
    }


    @RequestMapping("studentlogin.do")
    @ResponseBody
    public String studentlogin(HttpServletRequest request, @RequestParam("sno") String sno, @RequestParam("password") String password){
        boolean issuccess=false;
        Student student = studentService.login(sno, password);
        if(student!=null){
            issuccess=true;
            request.getSession().setAttribute("name",student.getName());
            request.getSession().setAttribute("sno",student.getSno());
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("servicemain.do")
    public String login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这里是login");
//        request.getRequestDispatcher("/WEB-INF/pages/service_taskmainui.jsp").forward(request,response);
        return "/service_taskmainui.jsp";
    }

    @RequestMapping("/datamain.do")
    public String serviceData(){
        return "/data_departmentUI.jsp";
    }

    @RequestMapping("studentmain.do")
    public void getMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Apply> group = applyService.group();
        for (Apply apply:group) {
            System.out.println("applyName:"+apply.getApplyName());
            for (Form form:apply.getFormList()) {
                System.out.println("    formName:"+form.getFormName()+"  link:"+form.getLink());
            }
        }
        request.setAttribute("group",group);
        request.getRequestDispatcher("/studentMain.jsp").forward(request,response);
    }



}
