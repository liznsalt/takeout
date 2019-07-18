package com.liznsalt.javatask.takeout.system.service.user;

import com.liznsalt.javatask.takeout.system.model.user.Shop;

import java.util.List;
import java.util.Map;

public interface ShopService {
    int add(Shop shop);//增
    int update(Shop shop);//改
    int deleteById(Integer sid);//删
    List<Map<String, Object>> queryAllShops(); // 所有店铺
    Shop queryShopById(Integer sid); // 通过ID查询店铺
    Shop queryShopByName(String sname);//通过名称查询

    Shop queryShopByPhone(String phone);
}