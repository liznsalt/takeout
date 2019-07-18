package com.liznsalt.javatask.takeout.system.dao.user;

import com.liznsalt.javatask.takeout.system.model.user.Shop;

import java.util.List;
import java.util.Map;

public interface ShopDao {
    int add(Shop shop); // 注册一个店铺
    int update(Shop shop); // 更新店铺的信息
    int deleteById(Integer sid); // 注销这个店铺
    List<Map<String, Object>> queryAllShops(); // 所有店铺
    Shop queryShopById(Integer sid); // 通过ID查询店铺
    List<Shop> queryShopByName(String sname); // 通过名字查询店铺
    Shop queryShopByPhone(String phone);
}