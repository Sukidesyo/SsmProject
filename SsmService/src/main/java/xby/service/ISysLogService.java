package xby.service;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xby.domain.SysLog;

import java.util.List;


public interface ISysLogService {
    List<SysLog> findAll();
    void save(SysLog sysLog);
}
