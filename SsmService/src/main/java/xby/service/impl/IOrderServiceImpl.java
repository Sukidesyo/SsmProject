package xby.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xby.dao.IOrderDao;
import xby.domain.Order;
import xby.service.IOrderService;

import java.util.List;

@Service
@Transactional
public class IOrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Order> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }
}
