package xby.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xby.domain.Permission;
import xby.domain.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Repository

public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "xby.dao.IPermissionDao.findByRoleId"))

    })
    List<Role> findByUserId(String id);
    @Select("select * from role")
    List<Role> findAll();
    @Insert("insert into role values(REPLACE (UUID(), '-', ''),#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "xby.dao.IPermissionDao.findByRoleId"))
    })
    Role findById(String roleId);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")

    List<Permission> findOtherPermissions(String roleId);
    @Insert("insert into role_permission  values(#{id},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("id") String id);
}
