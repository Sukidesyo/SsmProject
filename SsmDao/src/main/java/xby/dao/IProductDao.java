package xby.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xby.domain.Product;

import java.util.List;
@Repository

public interface IProductDao {
    @Select("select * from product")
    public List<Product> findAll();
    @Insert("insert into product(id,productNum,productName,cityName,departureTime," +
            "productPrice,productDesc,productStatus) values(REPLACE (UUID(), '-', ''),#{productNum},#{productName}" +
            ",#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product where id=#{productId}")
    Product findById(String productId);
}
