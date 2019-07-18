package com.liznsalt.javatask.takeout.system.dao.user;

import com.liznsalt.javatask.takeout.system.model.user.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopControllerDaoTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void add() {
        Shop shop= new Shop();
//        shop.setId(999);
        shop.setUsername("bgjakgjjhk");
        shop.setPassword("3131093");
        shop.setAddress("fahuajfj");
        System.out.println(this.shopDao.add(shop));
    }

    @Test
    public void update() {
        Shop shop = new Shop();
        shop.setId(1);
        shop.setUsername("dadafaf");
        shop.setPassword("4452133");
        shop.setAddress("fahuajfj");
        shop.setManagerName("Li Si");
        System.out.println(this.shopDao.update(shop));
    }

    @Test
    public void queryAllShops() {
        this.shopDao.queryAllShops()
                .forEach((x)-> {
                    x.forEach((key, value) -> System.out.println(key + ": " + value));
                });
    }

    @Test
    public void queryShopById() {
        Integer sid=1;
        System.out.println(this.shopDao.queryShopById(sid));
    }

    @Test
    public void queryShopByName() {
        String sname="Li Si";
        System.out.println(this.shopDao.queryShopByName(sname));
    }

    @Test
    public void deleteById() {
        Integer sid=7;
        System.out.println(this.shopDao.deleteById(sid));
    }
}