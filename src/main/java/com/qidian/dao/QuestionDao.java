package com.qidian.dao;

import com.qidian.domain.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao {
    /**
     * @Description: 查询所有Question
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from questions ")
    public List<Question> findAll();


    /**
     * @Description:  根据questionnarieId查询所有的问题
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from questions where questionnaireId=#{0}")
    public List<Question> findAllByQuestionnaireId(int questionnaireId);

    /**
     * @Description: 根据questionId查询问题
     * @Param:
     * @return:
     * @Date:
     */
    @Select("select * from questions where questionId=#{0}")
    public Question findByQuestionId(int questionId);


    @Select("<script>" +
            "select * from questions "+
            "<where>"+
            "<if test=\"type !=null\" > and type=#{type}</if>"+
            "<if test=\"stem !=null\" > <bind name=\"stem\" value=\"'%'+stem+'%'\"/> and stem like #{stem}</if>"+
            "<if test=\"validity !=null\" > and validity=#{validity}</if>"+
            "</where>"+
            "</script>")
    public List<Question> getQuestionList(@Param("type") String type, @Param("stem") String stem, @Param("validity") String validity);

    /**
     * @Description: 插入一条新的question
     * @Param:
     * @return:
     * @Date:
     */
    @Insert("insert into questions (type,stem,option1,option2,option3,option4,option5,questionnaireId,validity) values(#{type},#{stem},#{option1},#{option2},#{option3},#{option4},#{option5},#{questionnaireId},#{validity})")
    public boolean save(Question question);

    /**
     * @Description:  更新一个question
     * @Param:
     * @return:
     * @Date:
     */
    @Update("update questions set type=#{type},stem=#{stem},option1=#{option1},option2=#{option2},option3=#{option3},option4=#{option4},option5=#{option5},validity=#{validity} where questionId=#{questionId}")
    public boolean update(Question question);

    /**
     * @Description: 删除一个question
     * @Param:
     * @return:
     * @Date:
     */
    @Delete("delete from questions where questionId=#{questionId}")
    public boolean delete(@Param("questionId") int questionId);

    @Delete("<script> delete from questions where  questionId in (" +
            " <foreach collection='list' item='questionId' separator=','>" +
            "            #{questionId}" +
            " </foreach>" +
            "        ) </script>")
    public int deleteList(@Param("list") List<String> questionIdList);

    @Update("update questions set validity=#{validity} where questionId=#{questionId}")
    public boolean changeStatus(@Param("questionId")int questionId,@Param("validity")String validity);
}
