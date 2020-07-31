package xby.service.impl;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xby.dao.IRoleDao;
import xby.domain.Permission;
import xby.domain.Role;
import xby.service.IRoleService;

import javax.xml.soap.SOAPPart;
import java.util.List;
@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();

    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {

        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {

        return roleDao.findOtherPermissions(roleId);

    }

    @Override
    public void addPermissionToRole(String roleId,String[] ids) {
        System.out.println("roleId==="+roleId);
        for(String id:ids)
        {
            System.out.println("id====="+id);
            roleDao.addPermissionToRole(roleId,id);
        }
    }
}
