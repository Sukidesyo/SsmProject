package xby.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xby.domain.Role;
import xby.domain.UserInfo;

import java.util.List;

public interface IUserService extends UserDetailsService {
    UserInfo findByUsername(String id);
    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId,String[] roleId);
}
