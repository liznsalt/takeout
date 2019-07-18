package com.liznsalt.javatask.takeout.system.dao.goods;

import com.liznsalt.javatask.takeout.system.model.goods.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsDao {
    int add(Goods goods);
    int update(Goods goods);
    int deleteById(Integer gid);
    List<Goods> queryAllGoodsByShopId(Integer sid); // 找出该商家的所有商品
    List<Map<String, Object>> queryAllGoods(); // 所有商品，可以放在首页
    Goods queryGoodsById(Integer gid);// 通过ID查询商品
    List<Map<String, Object>> queryGoodsByName(String gname); // 通过名字查询商品
}
