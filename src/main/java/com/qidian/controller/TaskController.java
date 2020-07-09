package com.qidian.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Operator;
import com.qidian.domain.PageBean;
import com.qidian.domain.Task;
import com.qidian.service.TaskService;
import com.qidian.utils.Common;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("getTasks.do")
    @ResponseBody
    public List<Task> getTasks(){
        return taskService.findAll();
    }

    @RequestMapping("/main.do")
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
        PageHelper.orderBy("taskId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Task> tasks = taskService.findAllByOperator(username);
        System.out.println("tasks page:"+tasks);
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Task> pageBean=new PageBean<Task>(startPage,pageSize, (int) total);
            pageBean.setList(tasks);
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
        return  "/tasklist.jsp";
    }

    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public String changeStatus(HttpServletRequest request,@RequestParam int taskId,@RequestParam String status){
        boolean issuccess =false;
        System.out.println("taskID:"+taskId+"  status:"+status);
        issuccess=taskService.changeStatus(taskId,status);
        return String.valueOf(issuccess);
    }
    @RequestMapping("getaddTask.do")
    public String getaddTask(){
        return "/addtask.jsp";
    }
    @RequestMapping("/addTask.do")
    @ResponseBody
    public String addTask(HttpServletRequest request,@RequestParam String taskName,@RequestParam String taskDesc,@RequestParam String date){
        boolean issuccess =false;
        Task task=new Task();
        System.out.println("addTask------"+taskName+" "+taskDesc+" "+date);
        task.setTaskName(taskName);
        task.setTaskDesc(taskDesc);
        task.setStatus("1");
        try {
            task.setDatetime(new Timestamp(new Date().getTime()));
            String username=String.valueOf(request.getSession().getAttribute("username"));
            System.out.println(username);
            if(username!=null) {
                Operator operator = new Operator();
                operator.setUsername(username);
                System.out.println(operator);
                task.setOperator(operator);
                issuccess = taskService.save(task);
            }
            System.out.println(issuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("/deleteTask.do")
    public void deleteTask(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId")List<String> taskIdList,@RequestParam(defaultValue = "1") String page) throws IOException {
        System.out.println(taskIdList);
        try {
            taskService.deleteTaskList(taskIdList);
            Common.alertAndRedirect(response,"删除成功","main.do?page="+page);
        } catch (IOException e) {
            e.printStackTrace();
            Common.alertAndBack(response,e.getMessage());
        }
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String deleteOneTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int taskId){
        boolean issuccess=false;
        issuccess=taskService.delete(taskId);
        return String.valueOf(issuccess);
    }

    @RequestMapping("/getmodifyTask.do")
    public String getmodifyTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int taskId){
        Task task = taskService.findByTaskId(taskId);
        System.out.println(task);
        request.setAttribute("task",task);
        return "/modifyTask.jsp";
    }

    @RequestMapping("/modifyTask.do")
    @ResponseBody
    public String modifyTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int taskId, @RequestParam()String taskName,@RequestParam()String taskDesc,@RequestParam() String status){
        boolean issuccess =false;
        Task task=new Task();
        System.out.println("modifyTask------"+taskName+" "+taskDesc+" "+status);
        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setTaskDesc(taskDesc);
        task.setStatus(status);
        issuccess=taskService.update(task);
       return String.valueOf(issuccess);
    }



}
