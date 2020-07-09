import com.qidian.dao.AcademicDao;
import com.qidian.dao.AnswerDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AcademicDaoTest {

    private AcademicDao academicDao;

    @Before
    public void before(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        academicDao=ac.getBean("academicDao", AcademicDao.class);
    }

    @Test
    public void findAllName(){
        List<String> allName = academicDao.findAllName(1, 1);
        System.out.println(allName);
    }
}
