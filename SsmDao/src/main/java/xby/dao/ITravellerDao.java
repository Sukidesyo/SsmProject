package xby.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xby.domain.Traveller;

import java.util.List;
@Repository
public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId FROM order_traveller WHERE orderId=#{ordersId})")
    public List<Traveller> findById(String ordersId);
}
