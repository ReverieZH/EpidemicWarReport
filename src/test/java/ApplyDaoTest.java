import com.qidian.dao.ApplyDao;
import com.qidian.dao.QuestionDao;
import com.qidian.domain.Apply;
import com.qidian.domain.Form;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ApplyDaoTest {

    private ApplyDao applyDao;


    @Test
    public void  group(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        applyDao = ac.getBean("applyDao", ApplyDao.class);
        List<Apply> group = applyDao.group();
        System.out.println(group);
        for (Apply apply:group) {
            System.out.println("applyName:"+apply.getApplyName());
            for (Form form:apply.getFormList()) {
                System.out.println("    formName:"+form.getFormName()+"  link:"+form.getLink());
            }
        }
    }
}
