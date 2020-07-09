package com.qidian.service;

import com.qidian.domain.QRForm;
import com.qidian.domain.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QRFormService {

    public List<QRForm> findAll();

    public QRForm findByQrformId( int qrformId);

    public boolean save(QRForm qrForm);

    public boolean update(QRForm qrForm);

    public boolean delete( int qrformId);

    public int deleteQrFormList(List<String> qrformIdList);


    public boolean changeStatus(int qrformId,String status);

}
