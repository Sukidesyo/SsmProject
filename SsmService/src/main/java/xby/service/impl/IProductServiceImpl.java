package xby.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xby.dao.IProductDao;
import xby.domain.Product;
import xby.service.IProductService;

import java.util.List;
@Service
@Transactional
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
