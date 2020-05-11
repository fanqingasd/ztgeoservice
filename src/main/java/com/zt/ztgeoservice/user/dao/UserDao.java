package com.zt.ztgeoservice.user.dao;

import com.zt.ztgeoservice.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    //登录方法
    /*@Select("select * from zts_user where username=#{username}")*/
    public User login(String username);
}

