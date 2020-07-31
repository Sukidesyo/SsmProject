package xby.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xby.dao.IUserDao;
import xby.domain.Role;
import xby.domain.UserInfo;
import xby.service.IUserService;
import xby.utils.EncodeUtils;

import javax.xml.soap.SOAPPart;
import java.util.ArrayList;
import java.util.List;
@Service("IUserService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public UserInfo findByUsername(String id) {
       return userDao.findUserByUsername(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        UserInfo userInfo=null;
        userInfo= userDao.findUserByUsername(username);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true,true
                ,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles)
    {
        List<SimpleGrantedAuthority> list= new ArrayList<>();
        for(Role role:roles)
        {
            System.out.println(role.getRoleName());
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));

        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {

        return userDao.findAll();
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        String password = userInfo.getPassword();
        password= EncodeUtils.encodePassword(password);
        userInfo.setPassword(password);
        userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId,String[] roleId) {
        System.out.println("UserId+++++++===="+userId);
        for(String roleid:roleId)
        {
            System.out.println("roleidi====="+roleid);
            userDao.addRoleToUser(userId,roleid);
        }
    }
}
