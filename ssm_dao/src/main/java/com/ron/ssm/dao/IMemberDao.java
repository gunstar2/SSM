package com.ron.ssm.dao;

import com.ron.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    @Select("select * from Member where id = #{id}")
    public Member findById(String id) throws  Exception;
}
