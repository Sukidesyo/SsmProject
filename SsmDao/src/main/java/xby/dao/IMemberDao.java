package xby.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xby.domain.Member;

@Repository
public interface IMemberDao {
        @Select("select * from member where id=#{memberId}")
        public Member findById(String memberId);

}
