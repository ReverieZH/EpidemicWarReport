package com.qidian.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Apply;
import com.qidian.domain.Operator;
import com.qidian.domain.PageBean;
import com.qidian.domain.Task;
import com.qidian.service.ApplyService;
import com.qidian.service.TaskService;
import com.qidian.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @RequestMapping("/main.do")
    public String findByOperator(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        String username= String.valueOf(request.getSession().getAttribute("username"));
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("applyId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Apply> applyList = applyService.findAllByOperator(username);
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Apply> pageBean=new PageBean<Apply>(startPage,pageSize, (int) total);
            pageBean.setList(applyList);
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
        return  "/applylist.jsp";
    }

    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public String changeStatus(HttpServletRequest request,@RequestParam int applyId,@RequestParam String status){
        boolean issuccess =false;
        issuccess=applyService.changeStatus(applyId,status);
        return String.valueOf(issuccess);
    }
    @RequestMapping("getaddApply.do")
    public String getaddApply(){
        return "/addApply.jsp";
    }

    @RequestMapping("/addApply.do")
    @ResponseBody
    public String addTask(HttpServletRequest request,Apply apply){
          boolean issuccess =false;
            String username=String.valueOf(request.getSession().getAttribute("username"));
            System.out.println(username);
            if(username!=null) {
                apply.setUsername(username);
                issuccess = applyService.save(apply);
            }

        return String.valueOf(issuccess);
    }

    @RequestMapping("/deleteApply.do")
    public void deleteTask(HttpServletRequest request, HttpServletResponse response, @RequestParam("applyId")List<String> applyIdList,@RequestParam(defaultValue = "1") String page) throws IOException {
        try {
            applyService.deleteApplyList(applyIdList);
            Common.alertAndRedirect(response,"删除成功","main.do?page="+page);
        } catch (IOException e) {
            e.printStackTrace();
            Common.alertAndBack(response,e.getMessage());
        }
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String deleteOneTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int applyId){
        boolean issuccess=false;
        issuccess=applyService.delete(applyId);
        return String.valueOf(issuccess);
    }

    @RequestMapping("/getmodifyApply.do")
    public String getmodifyTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int applyId){
        Apply apply = applyService.findByApplyId(applyId);
        request.setAttribute("apply",apply);
        return "/modifyApply.jsp";
    }

    @RequestMapping("/modifyApply.do")
    @ResponseBody
    public String modifyTask(HttpServletRequest request, HttpServletResponse response, Apply apply){
        boolean issuccess =false;
        issuccess=applyService.update(apply);
       return String.valueOf(issuccess);
    }


    @RequestMapping("getApplyName.do")
    @ResponseBody
    public List<String> getApplyName(HttpServletRequest request){
        return applyService.findAllName();
    }
}
