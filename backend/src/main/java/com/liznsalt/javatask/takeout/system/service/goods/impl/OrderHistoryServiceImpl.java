package com.liznsalt.javatask.takeout.system.service.goods.impl;

import com.liznsalt.javatask.takeout.system.dao.goods.OrderHistoryDao;
import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;
import com.liznsalt.javatask.takeout.system.service.goods.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("OrderHistoryService")
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryDao orderHistoryDao;
    @Override
    public List<OrderHistory> queryAllOrderHistoryBySid(Integer sid) {
        return this.orderHistoryDao.queryAllOrderHistoryBySid(sid);
    }

    @Override
    public List<OrderHistory> queryAllOrderHistoryByCid(Integer cid) {
        return this.orderHistoryDao.queryAllOrderHistoryByCid(cid);
    }

    @Override
    public int deleteOrderHistoryByOid(int oid) {
        return this.orderHistoryDao.deleteOrderHistoryByOid(oid);
    }

    @Override
    public int add(OrderHistory orderHistory) {
        return this.orderHistoryDao.add(orderHistory);
    }

    @Override
    public List<Map<String, Object>> queryAllOrderHistory() {
        return this.orderHistoryDao.queryAllOrderHistory();
    }

    @Override
    public OrderHistory queryOrderHistoryByOid(Integer oid) {
        return this.orderHistoryDao.queryOrderHistoryByOid(oid);
    }

    @Override
    public int update(OrderHistory orderHistory) {
        return this.orderHistoryDao.update(orderHistory);
    }
}
