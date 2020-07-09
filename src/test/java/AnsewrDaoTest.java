import com.qidian.dao.AnswerDao;
import com.qidian.dao.QuestionDao;
import com.qidian.dao.TaskDao;
import com.qidian.domain.Answer;
import com.qidian.domain.Question;
import com.qidian.domain.Questionnaire;
import com.qidian.domain.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.*;

public class AnsewrDaoTest {

    private static AnswerDao answerDao;

    @Test
    public void testSave(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        answerDao=ac.getBean("answerDao", AnswerDao.class);

        List<Answer> answers=new ArrayList<>();
        Answer answer1=new Answer();
        answer1.setAnswerDate(new Timestamp(new Date().getTime()));
        Questionnaire questionnaire1=new Questionnaire();
        questionnaire1.setQuestionnaireId(15);
        answer1.setQuestionnaire(questionnaire1);
        Question question=new Question();
        question.setStem("今日情况1");
        answer1.setQuestion(question);
        answer1.setAnswer("正常");

        Answer answer2=new Answer();
        answer2.setAnswerDate(new Timestamp(new Date().getTime()));
        Questionnaire questionnaire2=new Questionnaire();
        questionnaire2.setQuestionnaireId(15);
        answer2.setQuestionnaire(questionnaire2);
        Question questio2=new Question();
        questio2.setStem("今日地区情况1");
        answer2.setQuestion(questio2);
        answer2.setAnswer("无");

        answers.add(answer1);
        answers.add(answer2);

        answerDao.saveAnsewrs(answers);
    }


    @Test
    public void testGroup(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        answerDao=ac.getBean("answerDao", AnswerDao.class);
        Map<String,String> map=new HashMap<>();
        String academic = map.get("academic");

        List<Student> group = answerDao.group( "电智学院","","","刘祎","本科生",15,"2020-07-05");
        for (Student stu:group) {
            System.out.println(stu);
            for (Answer ansewr:stu.getAnswers()) {
                System.out.println("--------"+ansewr);
            }
        }
    }
}
