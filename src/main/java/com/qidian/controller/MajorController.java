package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Academic;
import com.qidian.domain.Major;
import com.qidian.domain.PageBean;
import com.qidian.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;


    @RequestMapping("getMajors.do")
    @ResponseBody
    public List<Major> getMajors(@RequestParam()int academicId){
        return majorService.findByAcademicId(academicId);
    }


    @RequestMapping("main.do")
    public String majorMain(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("academicId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Major> majors = majorService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Major> pageBean=new PageBean<Major>(startPage,pageSize, (int) total);
            pageBean.setList(majors);
            request.setAttribute("pageBean",pageBean);
        }

        return "/majorlist.jsp";
    }

    @RequestMapping("getaddMajor.do")
    public String getaddMajor(){
        return "addMajor.jsp";
    }

    @RequestMapping("addMajor.do")
    @ResponseBody
    public String addMajor(Major major){
        boolean issuccess=false;
        major.setStatus("1");
        issuccess=majorService.save(major);
        return String.valueOf(issuccess);
    }

    @RequestMapping("deleteMajors.do")
    @ResponseBody
    public String deleteMajors(@RequestParam("majorId")List<String> majorIdList){
        boolean issuccess=false;
        int total=majorService.deleteMajorList(majorIdList);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(@RequestParam("majorId")int majorId){
        boolean issuccess=false;
        issuccess=majorService.delete(majorId);
        return String.valueOf(issuccess);
    }

    @RequestMapping("getmodifyMajor.do")
    public String getmodifyMajor(HttpServletRequest request,@RequestParam("majorId")int majorId){
        Major major = majorService.findByMajorId(majorId);
        request.setAttribute("major",major);
        return "/modifyMajor.jsp";
    }

    @RequestMapping("updateMajor.do")
    @ResponseBody
    public String updateMajor(Major major){
        boolean issuccess=false;
        issuccess = majorService.update(major);
        return String.valueOf(issuccess);
    }

    @RequestMapping("changeStatus.do")
    @ResponseBody
    public String changeStatus(@RequestParam("majorId")int majorId,@RequestParam("status")String status){
        boolean issuccess=false;
        issuccess=majorService.changeStatus(majorId,status);
        return String.valueOf(issuccess);
    }
}
