import com.qidian.dao.QuestionDao;
import com.qidian.domain.Question;
import com.qidian.domain.Questionnaire;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class QuestionDaoTest {

    private static QuestionDao questionDao;

    @Test
    public void findAll(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionDao = ac.getBean("questionDao", QuestionDao.class);
        List<Question> questions = questionDao.findAll();
        System.out.println(questions);
    }

    @Test
    public void findAllByQuestionnaireId(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionDao = ac.getBean("questionDao", QuestionDao.class);
        List<Question> questions = questionDao.findAllByQuestionnaireId(1);
        System.out.println(questions);
    }

    @Test
    public void findByQuestionId(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionDao = ac.getBean("questionDao", QuestionDao.class);
        Question question = questionDao.findByQuestionId(1);
        System.out.println(question);
    }


    @Test
    public void getQuestionsList(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionDao=ac.getBean("questionDao", QuestionDao.class);
        List<Question> questions=questionDao.getQuestionList("1","吃","1");
        System.out.println(questions);
    }


    @Test
    public void save(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionDao=ac.getBean("questionDao", QuestionDao.class);
        Question question=new Question();
        question.setType("选择");
        question.setStem("测试题1");
        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setQuestionnaireId(1);
        questionDao.save(question);
    }

    @Test
    public void update(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        questionDao=ac.getBean("questionDao", QuestionDao.class);
        Question question=new Question();
        question.setQuestionId(3);
        question.setType("选择");
        question.setStem("测试题1");
        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setQuestionnaireId(2);
        question.setValidity("0");
        questionDao.update(question);
    }
}
