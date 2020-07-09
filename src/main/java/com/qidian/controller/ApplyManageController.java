package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Apply;
import com.qidian.domain.Form;
import com.qidian.domain.PageBean;
import com.qidian.service.FormService;
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
@RequestMapping("/applyManage")
public class ApplyManageController {

    @Autowired
    private FormService formService;

    @RequestMapping("main.do")
    public String findByOperator(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        String username= String.valueOf(request.getSession().getAttribute("username"));
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("formId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Form> formList = formService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Form> pageBean=new PageBean<Form>(startPage,pageSize, (int) total);
            pageBean.setList(formList);
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
        return  "/formlist.jsp";
    }

    @RequestMapping("getaddLink.do")
    public String getaddLink(){
        return "/addLink.jsp";
    }

    @RequestMapping("/addForm.do")
    @ResponseBody
    public String addForm(HttpServletRequest request,Form form){
        boolean issuccess =false;
        String username=String.valueOf(request.getSession().getAttribute("username"));
        System.out.println(username);
        if(username!=null) {
            issuccess = formService.save(form);
        }
        return String.valueOf(issuccess);
    }

        @RequestMapping("getmodifyForm.do")
        public String getmodifyForm(HttpServletRequest request, HttpServletResponse response, @RequestParam()int formId){
            Form form = formService.findByFormId(formId);
            request.setAttribute("form",form);
            return "/modifyForm.jsp";
        }

        @RequestMapping("modifyForm.do")
        @ResponseBody
        public String modifyForm(HttpServletRequest request, HttpServletResponse response, Form form){
            boolean issuccess =false;
            System.out.println("modifyForm:    "+form.getLink());
           issuccess=formService.update(form);
            return String.valueOf(issuccess);
        }

        @RequestMapping("/changeStatus.do")
        @ResponseBody
        public String changeStatus(HttpServletRequest request,@RequestParam int formId,@RequestParam String status){
            boolean issuccess =false;
            issuccess=formService.changeStatus(formId,status);
            return String.valueOf(issuccess);
        }

        @RequestMapping("/deleteForm.do")
        public void deleteTask(HttpServletRequest request, HttpServletResponse response, @RequestParam("formId")List<String> formIdList,@RequestParam(defaultValue = "1") String page) throws IOException {
            try {
                formService.deleteFormList(formIdList);
                Common.alertAndRedirect(response,"删除成功","main.do?page="+page);
            } catch (IOException e) {
                e.printStackTrace();
                Common.alertAndBack(response,e.getMessage());
            }
        }

        @RequestMapping("delete.do")
        @ResponseBody
        public String deleteOneFrom(HttpServletRequest request, HttpServletResponse response, @RequestParam()int formId){
            boolean issuccess=false;
            issuccess=formService.delete(formId);
            return String.valueOf(issuccess);
        }

}
