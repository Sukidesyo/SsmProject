package xby.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xby.dao.ISysLogDao;
import xby.domain.SysLog;
import xby.service.ISysLogService;

import java.util.List;
@Service()
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
