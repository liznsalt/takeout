package com.liznsalt.javatask.takeout.system.service.user.impl;

import com.liznsalt.javatask.takeout.system.dao.user.ShopDao;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public int add(Shop shop) {
        return this.shopDao.add(shop);
    }

    @Override
    public int update(Shop shop) {
        return this.shopDao.update(shop);
    }

    @Override
    public int deleteById(Integer sid) {
        return this.shopDao.deleteById(sid);
    }

    @Override
    public List<Map<String, Object>> queryAllShops() {
        return this.shopDao.queryAllShops();
    }

    @Override
    public Shop queryShopById(Integer sid) {
        return this.shopDao.queryShopById(sid);
    }

    @Override
    public Shop queryShopByName(String sname) {
        if (this.shopDao.queryShopByName(sname) == null) {
            return null;
        }
        return this.shopDao.queryShopByName(sname).get(0);
    }

    @Override
    public Shop queryShopByPhone(String phone) {
        return this.shopDao.queryShopByPhone(phone);
    }


}
