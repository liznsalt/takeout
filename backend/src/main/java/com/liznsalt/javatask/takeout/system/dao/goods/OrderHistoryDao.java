package com.liznsalt.javatask.takeout.system.dao.goods;

import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;

import java.util.List;
import java.util.Map;

public interface OrderHistoryDao {
    // tips:建议利用orderHistory_view查询
    List<OrderHistory> queryAllOrderHistoryBySid(Integer sid); // 找出所有历史记录[给商家看]
    List<OrderHistory> queryAllOrderHistoryByCid(Integer cid); // 找出所有历史记录[给用户看]

    int deleteOrderHistoryByOid(int oid); // 删除此条历史纪录，主要oid是int型
    int add(OrderHistory orderHistory);

    List<Map<String, Object>> queryAllOrderHistory();
    OrderHistory queryOrderHistoryByOid(Integer oid);
    int update(OrderHistory orderHistory);
}
