package com.liznsalt.javatask.takeout.system.dao.goods;

import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao { // 注意这个购物车不是一整个购物栏，而是栏中的每一项预购
    int addShoppingCartItem(ShoppingCart shoppingCart); // 添加一项预购
    int updateShoppingCartItem(ShoppingCart shoppingCart); // 更新一项预购
    int deleteShoppingCartItemById(Integer cid, Integer gid); // 删除一项预购
    int clearShoppingCart(Integer cid); // 清空购物车，这里是删除所有预购
    int clearShoppingCartByCidSid(Integer cid, Integer sid);
    List<ShoppingCart> queryShoppingCartByCID(Integer CID);
    List<ShoppingCart> queryShoppingCartByCIDSID(Integer CID, Integer SID);
    ShoppingCart queryShoppingCartByID(Integer CID, Integer GID);
}
