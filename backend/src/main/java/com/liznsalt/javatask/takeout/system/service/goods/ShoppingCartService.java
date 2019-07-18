package com.liznsalt.javatask.takeout.system.service.goods;

import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    int addShoppingCartItem(ShoppingCart shoppingCart); // 添加一项预购
    int updateShoppingCartItem(ShoppingCart shoppingCart); // 更新一项预购
    int deleteShoppingCartItemById(Integer cid, Integer gid); // 删除一项预购
    int clearShoppingCart(Integer cid); // 清空购物车，这里是删除所有预购
    List<ShoppingCart> queryShoppingCartByCID(Integer CID);
    ShoppingCart queryShoppingCartByID(Integer CID, Integer GID);

    int clearShoppingCartByCidSid(Integer cid, Integer sid);
    List<ShoppingCart> queryShoppingCartByCIDSID(Integer CID, Integer SID);
}
