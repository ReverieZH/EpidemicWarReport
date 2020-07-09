import com.qidian.dao.AcademicDao;
import com.qidian.dao.MajorDao;
import com.qidian.domain.Major;
import com.qidian.service.MajorService;
import com.qidian.service.impl.MajorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MajorDaoTest {

    private MajorDao majorDao;

    private MajorService majorService;

    @Before
    public void before(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        majorDao=ac.getBean("majorDao", MajorDao.class);
        majorService=ac.getBean("majorService", MajorService.class);
    }

    @Test
    public void getMajors(){
        List<Major> majors = majorService.findByAcademicId(1);
        System.out.println(majors);
    }
}
