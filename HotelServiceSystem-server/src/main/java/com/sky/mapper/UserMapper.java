package com.sky.mapper;

import com.sky.entity.User;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    /**
     * 插入用户
     * @param user
     */
    void insert(User user);
}
