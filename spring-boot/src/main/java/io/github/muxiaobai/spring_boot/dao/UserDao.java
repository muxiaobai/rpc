package io.github.muxiaobai.spring_boot.dao;

import io.github.muxiaobai.spring_boot.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Select(" select * from user where username = #{username}")
    public List<User> query(String username);
}
