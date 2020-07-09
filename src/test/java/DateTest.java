import com.qidian.dao.AnswerDao;
import com.qidian.utils.Common;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    private static AnswerDao answerDao;
    @Test
    public void testDate(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Date date1=new Date(date.getTime()+86400000);
        String format = dateFormat.format(date);
        String format1 = dateFormat.format(date1);
        System.out.println(format);
        System.out.println(format1);
    }

    @Test
    public void testDate2(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        answerDao=ac.getBean("answerDao", AnswerDao.class);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=new Date();
        Date endDate=new Date(startDate.getTime()+86400000);
        String start = dateFormat.format(startDate);
        String end = dateFormat.format(endDate);
        int count = answerDao.selectCountBySno("201706060325", start, end);
        System.out.println(count);

    }
}
