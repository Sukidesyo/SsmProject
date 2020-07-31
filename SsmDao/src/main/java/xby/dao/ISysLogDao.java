package xby.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xby.domain.SysLog;

import java.util.List;
@Repository
public interface ISysLogDao {
    @Select("select * from syslog")
    List<SysLog> findAll();
    @Insert("insert into syslog values(REPLACE (UUID(), '-', ''),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);
}
