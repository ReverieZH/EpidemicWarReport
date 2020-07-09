package com.qidian.dao;

import com.qidian.domain.Questionnaire;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from questionnaire")
    public List<Questionnaire> findAll();

    /**
     * @Description: 根据taskId查询所有的questionnaire
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from questionnaire where taskId=#{0}")
    public List<Questionnaire>  findAllByTaskId(int taskId);

    @Select("select * from questionnaire where taskId=#{0}")
    @Results(value={
            @Result(property = "questionnaireId",column = "questionnaireId"),
            @Result(property = "queName",column = "queName"),
            @Result(property = "questionsList",column = "questionnaireId",many = @Many(select ="com.qidian.dao.QuestionDao.findAllByQuestionnaireId"))
             })
    public List<Questionnaire>  findAllAndQuestion(int taskId);

    @Select("select * from questionnaire where questionnaireId=#{0}")
    @Results(value={
            @Result(property = "questionnaireId",column = "questionnaireId"),
            @Result(property = "queName",column = "queName"),
            @Result(property = "questionsList",column = "questionnaireId",many = @Many(select ="com.qidian.dao.QuestionDao.findAllByQuestionnaireId"))
             })
    public Questionnaire  findAndQuestion(int questionnaireId);




    /**
     * 根据questionnarieId查询问卷信息
     * @param questionnaireId
     * @return
     */
    @Select("select * from questionnaire where questionnaireId=#{0}")
    public Questionnaire findByQuestionnarieId(int questionnaireId);

    @Select("<script>select * from questionnaire " +
            "<where> " +
            "<if test=\"name !=null \"> <bind name=\"name\" value=\"'%'+name+'%'\"/> and name like #{name} </if>  " +
            "<if test=\"status !=null \">  and status=#{status} </if> "+
            "<if test=\"taskId !=null \">  and taskId=#{taskId} </if>"+
            "</where>   " +
            "</script>")
    public List<Questionnaire> getQuestionnaireList(@Param("name") String name, @Param("status") String status, @Param("taskId") Integer taskId);

    /**
     * 新建问卷信息
     * @param questionnaire
     */
    @Insert("insert into questionnaire (queName,queDesc,datetime,status,taskId) values (#{queName},#{queDesc},#{datetime},#{status},#{taskId})")
    public boolean save(Questionnaire questionnaire);

    /**
     * 更新问卷信息
     * @param questionnaire
     */
    @Update("update questionnaire set queName=#{queName},queDesc=#{queDesc},status=#{status} where questionnaireId=#{questionnaireId} ")
    public boolean update(Questionnaire questionnaire);

    /**
     * 根据questionnarieId删除问卷信息
     * @param questionnaireId
     */
    @Delete("delete from questionnaire where questionnaireId=#{questionnaireId}")
    public boolean delete(@Param("questionnaireId") int questionnaireId);

    @Delete("<script> delete from questionnaire where  questionnaireId in (" +
            " <foreach collection='list' item='questionnaireId' separator=','>" +
            "            #{questionnaireId}" +
            " </foreach>" +
            "        ) </script>")
    public int deleteList(@Param("list") List<String> questionnaire);

    @Update("update questionnaire set status=#{status} where questionnaireId=#{questionnaireId}")
    public boolean changeStatus(@Param("questionnaireId")int questionnaireId,@Param("status")String status);



}
