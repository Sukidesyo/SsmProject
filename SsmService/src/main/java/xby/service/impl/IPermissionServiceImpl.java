package xby.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xby.dao.IPermissionDao;
import xby.domain.Permission;
import xby.service.IPermissionService;

import java.util.List;

@Service
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {

        return permissionDao.finAll();
    }

    @Override
    public Permission findById(String permissionId) {
        return permissionDao.findById(permissionId);
    }

    @Override
    public void deleteById(String permissionId) {
        permissionDao.deleteById(permissionId);
    }

    @Override
    public void deleteForeign(String permissionId) {
        permissionDao.deleteForeign(permissionId);
    }
}
