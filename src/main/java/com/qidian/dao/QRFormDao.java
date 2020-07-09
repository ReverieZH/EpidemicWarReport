package com.qidian.dao;

import com.qidian.domain.QRForm;
import com.qidian.domain.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface QRFormDao {

    @Select("select * from qrform")
    public List<QRForm> findAll();

    @Select("select * from qrform where qrformId=#{qrformId}")
    public QRForm findByQrformId(@Param("qrformId") int qrformId);

    @Insert("insert into qrform (qrformName,qrformDesc,datetime,questionnaireId,status) value (#{qrformName},#{qrformDesc},#{datetime},#{questionnaireId},#{status})")
    public boolean save(QRForm qrForm);

    /**
     * 更新任务信息
     * @param qrForm
     */
    @Update("update qrform set qrformName=#{qrformName},qrformDesc=#{qrformDesc},status=#{status} where qrformId=#{qrformId} ")
    public boolean update(QRForm qrForm);

    /**
     * 根据qrformId删除任务
     * @param qrformId
     */
    @Delete("delete from qrform where qrformId=#{qrformId}")
    public boolean delete(@Param("qrformId") int qrformId);

    @Delete("<script> delete from qrform where qrformId in"+
            "<foreach collection=\"list\" item=\"qrformId\" open=\"(\" close=\")\" separator=\",\">"+
            "#{qrformId}"+
            "</foreach>"+
            "</script>")
    public int deleteQrFormList(List<String> qrformIdList);
    /**
     * @Description:  根据taskId禁用任务
     * @Date:
     * @Author:
     */

    @Update("update qrform set status=#{status} where qrformId=#{qrformId}")
    public boolean changeStatus(@Param("qrformId")int qrformId,@Param("status")String status);

}
