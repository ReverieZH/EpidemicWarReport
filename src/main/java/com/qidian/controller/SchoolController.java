package com.qidian.controller;

import com.qidian.domain.School;
import com.qidian.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/school")
public class SchoolController{

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<School> schoolList = schoolService.findAll();
        mv.addObject("schoolList",schoolList);
        mv.setViewName("school-list");
        return mv;
    }

    @RequestMapping("/findBySchoolId.do")
    public ModelAndView findBySchoolId(@RequestParam(name = "schoolId",required = true) String schoolId){
        ModelAndView mv = new ModelAndView();
        School school = schoolService.findBySchoolId(schoolId);
        mv.addObject("school",school);
        mv.setViewName("school-update");
        return mv;
    }

    @RequestMapping("/update.do")
    public String update(School school){
        schoolService.update(school);
        return "redirect:findAll.do";
    }


    @RequestMapping("/save.do")
    public String save(School school){
        schoolService.save(school);
        return "redirect:findAll.do";
    }

    @RequestMapping("/delete.do")
    public String delete(@RequestParam(name = "schoolId",required = true) String schoolId){
        schoolService.delete(schoolId);
        return "redirect:findAll.do";
    }
}
