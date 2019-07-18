package com.liznsalt.javatask.takeout.system.dao.goods;

import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartDaoTest {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Test
    public void addShoppingCartItem() {
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setCid(1);
        shoppingCart.setGid(1);
        shoppingCart.setNum(10);
        shoppingCart.setTotal(1.00);
        System.out.println(this.shoppingCartDao.addShoppingCartItem(shoppingCart));
    }

    @Test
    public void updateShoppingCartItem() {
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setNum(20);
        shoppingCart.setTotal(200.00);
        shoppingCart.setCid(002);
        shoppingCart.setGid(002);
        System.out.println(this.shoppingCartDao.updateShoppingCartItem(shoppingCart));

    }

    @Test
    public void deleteShoppingCartItemById() {
        System.out.println(this.shoppingCartDao.deleteShoppingCartItemById(001,001));
    }

    @Test
    public void clearShoppingCart() {
        System.out.println(this.shoppingCartDao.clearShoppingCart(002) );
    }
}