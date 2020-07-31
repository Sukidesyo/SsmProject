package xby.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xby.domain.Member;
import xby.domain.Order;
import xby.domain.Product;

import java.util.List;

@Repository

public interface IOrderDao {
    @Select("select * from orders")
    List<Order> findAll();
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,one=@One(select = "xby.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class,one=@One(select = "xby.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id",javaType = java.util.List.class,many = @Many(select = "xby.dao.ITravellerDao.findById")),
    }
    )
    Order findById(String ordersId);
}
