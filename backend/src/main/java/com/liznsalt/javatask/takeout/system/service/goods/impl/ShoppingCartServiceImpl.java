package com.liznsalt.javatask.takeout.system.service.goods.impl;


import com.liznsalt.javatask.takeout.system.dao.goods.ShoppingCartDao;
import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.goods.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public int addShoppingCartItem(ShoppingCart shoppingCart) {
        return this.shoppingCartDao.addShoppingCartItem(shoppingCart);
    }

    @Override
    public int updateShoppingCartItem(ShoppingCart shoppingCart) {
        return this.shoppingCartDao.updateShoppingCartItem(shoppingCart);
    }

    @Override
    public int deleteShoppingCartItemById(Integer cid, Integer gid) {
        return this.shoppingCartDao.deleteShoppingCartItemById(cid, gid);
    }

    @Override
    public int clearShoppingCart(Integer cid) {
        return this.shoppingCartDao.clearShoppingCart(cid);
    }

    @Override
    public List<ShoppingCart> queryShoppingCartByCID(Integer CID) {
        return this.shoppingCartDao.queryShoppingCartByCID(CID);
    }

    @Override
    public ShoppingCart queryShoppingCartByID(Integer CID, Integer GID) {
        return this.shoppingCartDao.queryShoppingCartByID(CID, GID);
    }

    @Override
    public int clearShoppingCartByCidSid(Integer cid, Integer sid) {
        return this.shoppingCartDao.clearShoppingCartByCidSid(cid, sid);
    }

    @Override
    public List<ShoppingCart> queryShoppingCartByCIDSID(Integer CID, Integer SID) {
        return this.shoppingCartDao.queryShoppingCartByCIDSID(CID, SID);
    }
}
