package xby.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import xby.domain.Permission;

import javax.jws.HandlerChain;
import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findByRoleId(String id);


    @Select("select * from permission")
    List<Permission> finAll();
    @Select("select * from permission where id=#{permissionId}")
    Permission findById(String permissionId);

    @Delete("delete from permission where id=#{permissionId}")
    void deleteById(String permissionId);
    @Delete("delete from role_permission where permissionId=#{permissionId}")
    void deleteForeign(String permissionId);
}
