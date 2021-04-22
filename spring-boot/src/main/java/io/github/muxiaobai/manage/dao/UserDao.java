//package io.github.muxiaobai.manage.dao;
//
//import io.github.muxiaobai.manage.vo.User;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
//import org.apache.ibatis.annotations.Select;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Mapper
//@Repository
//public interface UserDao {
//    @Select(" select * from user where username = #{username}")
//    public List<User> query(String username);
//
//    @Insert(" insert into user (username,password) values ( #{username},#{password})")
//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
//    public  Integer insert(User user);
//}
