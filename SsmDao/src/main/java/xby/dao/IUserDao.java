package xby.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import xby.domain.Role;
import xby.domain.UserInfo;

import java.util.List;

@Repository
public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "xby.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findUserByUsername(String username);
    @Select("select * from users ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "xby.dao.IRoleDao.findByUserId"))
    })
    List<UserInfo> findAll();
    @Select("insert into users values(REPLACE (UUID(), '-', ''),#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "xby.dao.IRoleDao.findByUserId"))
    })
    UserInfo findById(String id);



    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherRoles(String id);

    @Select("insert into users_role (userId,roleId) values(#{userId},#{roleid})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleid") String roleid);
}
