package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.Campus;
import com.qidian.domain.PageBean;
import com.qidian.domain.Task;
import com.qidian.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/campus")
public class CampusController {


    @Autowired
    private CampusService campusService;

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
        PageHelper.orderBy("campusId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Campus> campusList = campusService.findAllBySchoolId(1);
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<Campus> pageBean=new PageBean<Campus>(startPage,pageSize, (int) total);
            pageBean.setList(campusList);
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
        return  "/campuslist.jsp";
    }

}
