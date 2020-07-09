package com.qidian.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Form;
import com.qidian.domain.Operator;
import com.qidian.domain.PageBean;
import com.qidian.domain.Student;
import com.qidian.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping("main.do")
    public String getOperators(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("academicName ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Operator> operatorList = operatorService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Operator> pageBean=new PageBean<Operator>(startPage,pageSize, (int) total);
            pageBean.setList(operatorList);
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
        return  "/operatorlist.jsp";
    }

    @RequestMapping("getaddOperator.do")
    public String getaddOperator(){
        return "addOperator.jsp";
    }


    @RequestMapping("addOperator.do")
    @ResponseBody
    public String addStudent(Operator operator){
        boolean issuccess=false;
        operator.setValidty("1");
        issuccess=operatorService.save(operator);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getmodifyOperator.do")
    public String getmodifyOperator(HttpServletRequest request,@RequestParam String username){
        Operator operator=operatorService.findByUsername(username);
        request.setAttribute("operator",operator);
        return "modifyOperator.jsp";
    }
    @RequestMapping("getmodifyInform.do")
    public String getmodifyInform(HttpServletRequest request,@RequestParam String username){
        Operator operator=operatorService.findByUsername(username);
        request.setAttribute("operator",operator);
        return "modifyInform.jsp";
    }

    @RequestMapping("modifyOperator.do")
    @ResponseBody
    public String modifyOperator(Operator operator){
        boolean issuccess=false;
        issuccess=operatorService.update(operator);
        return String.valueOf(issuccess);
    }
    @RequestMapping("modifyInform.do")
    @ResponseBody
    public String modifyInform(Operator operator){
        boolean issuccess=false;
        issuccess=operatorService.updateInform(operator);
        return String.valueOf(issuccess);
    }

    @RequestMapping("deleteOperator.do")
    @ResponseBody
    public String deleteMajors(@RequestParam("username")List<String> usernamelist){
        boolean issuccess=false;
        int total=operatorService.deleteOperatorList(usernamelist);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }


    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(@RequestParam("username")String username){
        boolean issuccess=false;
        issuccess=operatorService.delete(username);
        return String.valueOf(issuccess);
    }

    @RequestMapping("changeStatus.do")
    @ResponseBody
    public String changeStatus(@RequestParam("username")String username,@RequestParam("status")String status){
        boolean issuccess=false;
        issuccess=operatorService.changeStatus(username,status);
        return String.valueOf(issuccess);
    }

}
