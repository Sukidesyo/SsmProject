package xby.service;

import xby.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll();

    Permission findById(String permissionId);

    void deleteById(String permissionId);

    void deleteForeign(String permissionId);
}
