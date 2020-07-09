package com.qidian.dao;

import com.qidian.domain.Operator;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorDao {

    /**
     * 操作者登录
     * @param username
     * @param password
     * @return
     */
    @Select("select * from operator where username=#{username} and password=#{password}")
    public Operator login(@Param("username") String username, @Param("password") String password);


    @Select("select * from operator")
    public List<Operator> findAll();

    @Select("select * from operator where username=#{username}")
    public Operator findByUsername(@Param("username") String username);

    @Insert("insert into operator(username, password, name, role, academicName, validty) value(#{username}, #{password}, #{name}, #{role}, #{academicName}, #{validty})")
    public boolean save(Operator operator);

    @Update("update operator set username=#{username},password=#{password},name=#{name},role=#{role},academicName=#{academicName},validty=#{validty} where username=#{username}")
    public boolean update(Operator operator);

    @Delete("delete from operator where username=#{username}")
    public boolean delete(String username);

    @Delete("<script> delete from operator where username in"+
            "<foreach collection=\"list\" item=\"username\" open=\"(\" close=\")\" separator=\",\">"+
            "#{username}"+
            "</foreach>"+
            "</script>")
    public int deleteOperatorList(List<String> usernameList);

    @Update("update operator set status=#{status} where username=#{username}")
    public boolean changeStatus(String username, String status);

    @Update("update operator set password=#{password},name=#{name} where username=#{username}")
    public boolean updateInform(Operator operator);
}
