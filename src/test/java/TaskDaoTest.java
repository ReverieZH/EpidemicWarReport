import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qidian.dao.TaskDao;
import com.qidian.domain.Operator;
import com.qidian.domain.Task;
import com.qidian.service.TaskService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDaoTest {
    TaskDao taskDao;
    TaskService taskService;
    @Test
    public void findAll() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskDao=ac.getBean("taskDao", TaskDao.class);
        List<Task> tasks=taskDao.findAll();
        System.out.println(tasks);
    }

    @Test
    public void findByQuestionnarieId() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskDao=ac.getBean("taskDao", TaskDao.class);
        List<Task> tasks = taskDao.findAllByOperator("reverie");
        System.out.println(tasks);
    }

    @Test
    public void getTaskList(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskDao=ac.getBean("taskDao", TaskDao.class);
//        List<Task> taskList = taskDao.getTaskList(null, "统计", "2020-01-01", "2020-05-12");
        List<Task> taskList = taskDao.getTaskList(null, null, "2020-01-01", "2020-05-12");
        System.out.println(taskList);
    }
    @Test
    public void save() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskDao=ac.getBean("taskDao", TaskDao.class);
        Task task=new Task();
        task.setTaskName("测试1111111111111111");
        task.setTaskDesc("测试task");
        task.setDatetime(new Timestamp(new Date().getTime()));
        Operator operator=new Operator();
        operator.setUsername("reverie");
        task.setOperator(operator);
        taskDao.save(task);
    }

    @Test
    public void update() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskDao=ac.getBean("taskDao", TaskDao.class);
        Task task=new Task();
        task.setTaskId(5);
        task.setTaskName("测试");
        task.setTaskDesc("测试task改变");
        task.setDatetime(new Timestamp(new Date().getTime()));
        Operator operator=new Operator();
        operator.setUsername("reverie");
        task.setOperator(operator);
        taskDao.update(task);
    }

    @Test
    public void delete() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskService=ac.getBean("taskService", TaskService.class);
        taskService.delete(1);
    }

    @Test
    public void page(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskService=ac.getBean("taskService", TaskService.class);
        Page page= PageHelper.startPage(0, 10);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("taskId ASC");
        // 从数据库查询，这里返回的的questionnaires就已经分页成功了
        List<Task> tasks = taskService.findAllByOperator("reverie");
        System.out.println("tasks page:"+tasks);
    }



    @Test
    public void changeStatus(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskService=ac.getBean("taskService", TaskService.class);
        taskService.changeStatus(1,"0");
    }


    @Test
    public void deleteTaskList(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        taskService=ac.getBean("taskService", TaskService.class);
        List<String> taskList=new ArrayList<>();
        taskList.add("3");
        taskList.add("4");
        int number=taskService.deleteTaskList(taskList);
        System.out.println(number);
    }

    @Test
    public void testDate(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=dateFormat.parse("2020-06-26");
            Date now=new Date();
            System.out.println(dateFormat.format(date));
            System.out.println(new Timestamp(new Date().getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  num(){
        System.out.println(10/4+1);
        int cloumn=1;
        for (int i=0;i<(10/4+1)*4;i++) {

//            System.out.print("cloumn%4:"+cloumn%4+" ");
            if(i>10) {
                System.out.print(0 + " ");
            }
            else {
                System.out.print(i + " ");
            }
            if ((cloumn%4)==0) {
//                System.out.println( "cloumn:"+cloumn);
                System.out.println();
                cloumn=1;
                continue;
            }
            cloumn++;
        }

    }
}
