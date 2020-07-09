package com.qidian.dao;

import com.qidian.domain.Answer;
import com.qidian.domain.Apply;
import com.qidian.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnswerDao {

    /**
     * @Description: 查询所有Answer
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from answers ")
    public List<Answer> findAll();

    /**
     * @Description:  根据questionnarieId查询所有的问题
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from answers where questionnaireId=#{0}")
    public List<Answer> findAllByQuestionnarieId(int questionnaireId);



    /**
     * @Description:  根据questionnarieId查询所有的问题
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from answers where answerdate&gt;=#{0} and answerdate &lt;={1} and questionnaireId=#{2}")
    public List<Answer> getAnswerList(Date startDate, Date endDate, int questionnaireId);

    @Select("select * from answers where sno=#{sno} and substr(answerDate,1,10)=#{answerDate} and questionnaireId=#{questionnaireId}")
    public List<Answer> selectBySno(@Param("sno") String sno,@Param("answerDate") String answerDate,@Param("questionnaireId") int  questionnaireId);

   /**
     * @Description: 插入一条新的answer
     * @Param:
     * @return:
     * @Date:
     */
    @Insert("insert into answers (answerdate,sno,questionnaireid,stem,answer) values(#{answerid},#{answerdate},#{sno},#{questionnaireid},#{questionid},#{answer})")
    public int save(Answer answer);

   /* @Insert("<scirpt>"
            +"insert into answers (answerid,answerdate,sno,questionnaireid,stem,answer) VALUES"
            +"<foreach collection=\"list\" item=\"answers\" separator=\",\">\n" +
            "  (#{answers.answerDate},#{answers.sno},#{answers.questionnaire.questionnaireId},#{answers.question.stem},#{answers.answer})\n" +
            "   </foreach>"
             +"</script>" )*/
    @Insert({
            "<script>",
            "insert into answers (answerdate,sno,questionnaireid,stem,answer) values ",
            "<foreach collection='testLists' item='answers' index='index' separator=','>",
            "(#{answers.answerDate},#{answers.sno},#{answers.questionnaire.questionnaireId},#{answers.question.stem},#{answers.answer})",
            "</foreach>",
            "</script>"
    })
    public int saveAnsewrs(@Param(value="testLists") List<Answer> answers);

    @Select("select count(*) from answers where sno=#{sno} and answerDate >#{startDate} and answerDate < #{endDate}")
    public int selectCountBySno(@Param("sno") String sno,@Param("startDate") String startDate,@Param("endDate") String endDate);


    @Select("<script>" +
            "select sno, name ,academicName,IFNULL(#{answerDate},'') answerDate,IFNULL(#{questionnaireId},'') questionnaireId from students"+
            "<where> "+
            "<if test=\"academicName!=null and academicName!=''\" >"+
            "and academicName=#{academicName}"+
            "</if>"+
            "<if test=\"majors!=null and majors!=''\" >"+
            "and majors=#{majors}"+
            "</if>"+
            "<if test=\"className!=null and className!=''\" >"+
            "and className=#{className}"+
            "</if>"+
            "<if test=\"name!=null and name!=''\" >"+
            "and name like concat(concat('%',#{name}),'%')"+
            "</if>"+
            "<if test=\"type!=null and type!=''\" >"+
            "and type=#{type}"+
            "</if>"+
            "</where>"+
            "</script>")
    @Results(value = {
            @Result(property = "name",column = "name"),
            @Result(property = "sno",column = "sno"),
            @Result(property = "answers",column = "{sno=sno,questionnaireId=questionnaireId,answerDate=answerDate}",many = @Many(select = "com.qidian.dao.AnswerDao.selectBySno"))   })
    public List<Student>  group(@Param("academicName")String academicName,@Param("majors")String majors,@Param("className")String className,@Param("name")String name,@Param("type")String type,@Param("questionnaireId") int  questionnaireId,@Param("answerDate") String answerDate);



}
