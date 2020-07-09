import com.qidian.dao.QuestionnaireDao;
import com.qidian.domain.Questionnaire;
import com.qidian.service.QuestionnaireService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class QuestionnarieDaoTest {

    QuestionnaireDao questionnaireDao;
    QuestionnaireService questionnaireService;

    @Test
    public void findAll() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beans=ac.getBeanDefinitionNames();
        for (String s:beans
             ) {
            System.out.println(s);
        }
        System.out.println();
        questionnaireDao=ac.getBean("questionnaireDao", QuestionnaireDao.class);
        List<Questionnaire> questionnaireList=questionnaireDao.findAll();
        System.out.println(questionnaireList);
    }

    @Test
    public void findByQuestionnarieId() {
    }

    @Test
    public void save() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionnaireDao=ac.getBean("questionnaireDao", QuestionnaireDao.class);
        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setQueName("每日打卡");
        questionnaire.setQueDesc("每日打卡");
        questionnaire.setDatetime(new Timestamp(new Date().getTime()));
        questionnaire.setStatus("1");
        questionnaire.setTaskId(19);
        questionnaireDao.save(questionnaire);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() { ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beans=ac.getBeanDefinitionNames();
        questionnaireService=ac.getBean("questionnaireService", QuestionnaireService.class);
        questionnaireService.delete(1);
    }
}