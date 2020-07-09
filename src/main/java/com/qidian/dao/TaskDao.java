package com.qidian.dao;

import com.qidian.domain.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from task")
    public List<Task> findAll();

    /**
     * @Description: 根据operatorId查询所有的task
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from task where username=#{0}")
    public List<Task>  findAllByOperator(String username);

    /**
     * 根据taskId查询任务信息
     * @param taskId
     * @return
     */
    @Select("select * from task where taskId=#{0}")
    public Task findByTaskId(int taskId);

    @Select("<script> select * from task " +
            "<where> " +
            "<if test=\"taskName !=null \"> <bind name=\"taskName\" value=\"'%'+taskName+'%'\"/> and taskName like #{taskName} </if>  " +
            "<if test=\"taskDesc !=null \"> <bind name=\"taskDesc\" value=\"'%'+taskDesc+'%'\"/> and taskDesc like #{taskDesc} </if> "+
            "<if test=\"startTime !=null and endTime !=null \">  and dateTime &gt;=#{startTime} and dateTime &lt;=#{endTime} </if>"+
            "</where>   " +
            "</script>")
    public List<Task> getTaskList(@Param("taskName") String taskName, @Param("taskDesc") String taskDesc, @Param("startTime") String startTime, @Param("endTime") String endTime);
    /**
     * 新建任务
     * @param task
     */
    @Insert("insert into task (taskName,taskDesc,datetime,username,status) value (#{taskName},#{taskDesc},#{datetime},#{operator.username},#{status})")
    public boolean save(Task task);

    /**
     * 更新任务信息
     * @param task
     */
    @Update("update task set taskName=#{taskName},taskDesc=#{taskDesc},status=#{status} where taskId=#{taskId} ")
    public boolean update(Task task);

    /**
     * 根据taskId删除任务
     * @param taskId
     */
    @Delete("delete from task where taskId=#{taskId}")
    public boolean delete(@Param("taskId") int taskId);

    @Delete("<script> delete from task where taskId in"+
            "<foreach collection=\"list\" item=\"taskId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{taskId}"+
            "</foreach>"+
             "</script>")
    public int deleteTaskList(List<String> taskIdList);
    /**
    * @Description:  根据taskId禁用任务
    * @Date:
    * @Author:
    */

    @Update("update task set status=#{status} where taskId=#{taskId}")
    public boolean changeStatus(@Param("taskId")int taskId,@Param("status")String status);

}
