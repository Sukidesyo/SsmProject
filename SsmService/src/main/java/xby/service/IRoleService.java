package xby.service;

import xby.domain.Permission;
import xby.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    void save(Role role);

    Role findById(String roleId);

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId,String[] ids);
}
