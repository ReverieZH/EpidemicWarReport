package com.qidian.controller;


import com.qidian.domain.Answer;
import com.qidian.domain.Question;
import com.qidian.domain.Questionnaire;
import com.qidian.service.AnsewrService;
import com.qidian.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnsewrService ansewrService;

    @RequestMapping("/main.do")
    public void main(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> data) throws IOException {
        int questionnaireId = Integer.parseInt(data.remove("questionnaireId"));
        String sno = data.remove("sno");

        List<Answer> answers=new ArrayList<>();
        for (String stem:data.keySet()) {
            Answer answer=new Answer();
            answer.setAnswerDate(new Timestamp(new Date().getTime()));
            answer.setSno(sno);
            answer.setQuestionnaire(new Questionnaire(questionnaireId));
            answer.setQuestion(new Question(stem));
            answer.setAnswer(data.get(stem));
            answers.add(answer);
        }
        ansewrService.saveAnsewrs(answers);
        Common.alertAndBack(response,"保存成功");
    }
}
