package com.qidian.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.domain.PageBean;
import com.qidian.domain.QRForm;
import com.qidian.domain.Question;
import com.qidian.domain.Task;
import com.qidian.service.QRFormService;
import com.qidian.utils.Common;
import com.qidian.utils.GetQRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/qrcode")
public class QRFormController {

    @Autowired
    private QRFormService qrFormService;

    @RequestMapping("main.do")
    public String getQrFormList(HttpServletRequest request, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String limit){
        System.out.println("questionMain--------------");

        // 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
        int startPage= Integer.parseInt(page);
        int pageSize= Integer.parseInt(limit);


        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page pager= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("qrformId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<QRForm> qrFormList = qrFormService.findAll();
        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = pager.getTotal();
        System.out.println("total------>"+total);
        if(total!=0){
            PageBean<QRForm> pageBean=new PageBean<QRForm>(startPage,pageSize, (int) total);
            pageBean.setList(qrFormList);
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
        return  "/qrformlist.jsp";
    }

    @RequestMapping("getQrCode.do")
    public String getQrCode(HttpServletRequest request,@RequestParam int questionnaireId){
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";

        String content=basePath+"question/getQuestionnaire.do?questionnaireId="+questionnaireId;
//        String contexPath= request.getSession().getServletContext().getRealPath("/");

        String imgpath="D:\\EpidemicReport\\src\\main\\webapp\\img\\qrform"+questionnaireId+".png";
        GetQRCode.getURCodePicture(content,imgpath);
        request.setAttribute("name","img/qrform"+questionnaireId+".png");
        return "/getQrCode.jsp";
    }


    @RequestMapping("getaddQrForm.do")
    public String getaddQrForm(){
        return "addQrForm.jsp";
    }

    @RequestMapping("addQrForm")
    @ResponseBody
    public String addQrForm(QRForm qrForm){
        boolean issuccess=false;
        qrForm.setDatetime(new Timestamp(new Date().getTime()));
        issuccess=qrFormService.save(qrForm);
        return String.valueOf(issuccess);
    }


    @RequestMapping("/getmodifyQrForm.do")
    public String getmodifyQrForm(HttpServletRequest request, HttpServletResponse response, @RequestParam()int qrformId){
        QRForm qrForm = qrFormService.findByQrformId(qrformId);
        request.setAttribute("qrForm",qrForm);
        return "/modifyQrForm.jsp";
    }

    @RequestMapping("/modifyQrForm.do")
    @ResponseBody
    public String modifyTask(HttpServletRequest request, HttpServletResponse response, QRForm qrForm){
        boolean issuccess =false;
        issuccess=qrFormService.update(qrForm);
        return String.valueOf(issuccess);
    }

    @RequestMapping("/deleteQrForm.do")
    @ResponseBody
    public String deleteTask(HttpServletRequest request, HttpServletResponse response, @RequestParam("qrformId")List<String> qrformIdList) throws IOException {
        boolean issuccess=false;
        int total=qrFormService.deleteQrFormList(qrformIdList);
        if(total>0){
            issuccess=true;
        }
        return String.valueOf(issuccess);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String deleteOneTask(HttpServletRequest request, HttpServletResponse response, @RequestParam()int qrformId){
        boolean issuccess=false;
        issuccess=qrFormService.delete(qrformId);
        return String.valueOf(issuccess);
    }
}
