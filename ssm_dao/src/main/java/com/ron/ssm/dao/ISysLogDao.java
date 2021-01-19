package com.ron.ssm.dao;

import com.ron.ssm.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method)" +
            " values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(Syslog syslog)throws Exception;

    @Select("Select * from sysLog ")
    List<Syslog> findAll()throws Exception;
}
