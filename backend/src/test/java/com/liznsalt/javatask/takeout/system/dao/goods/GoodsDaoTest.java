package com.liznsalt.javatask.takeout.system.dao.goods;

import com.liznsalt.javatask.takeout.system.model.goods.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsDaoTest {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void test() {
        Goods goods = new Goods();
        System.out.println(goods);
    }

    @Test
    public void add() {
        Goods goods = new Goods();
//        goods.setId(2);
        goods.setName("lalali");
        goods.setPrice(20.2);
        goods.setStock(20);
        goods.setSid(1);
        goods.setType("饮料");
        goods.setBuyCount(1);
        System.out.println(this.goodsDao.add(goods));

    }

    @Test
    public void update() {
        Goods goods = new Goods();
        goods.setId(2);
        goods.setName("RRRRR");
        goods.setPrice(20.2);
        goods.setStock(20);
        goods.setType("饮料");
        goods.setBuyCount(1);
        goods.setSid(1);
        System.out.println(this.goodsDao.update(goods));
    }

    @Test
    public void queryAllGoodsByShopId() {
//        System.out.println(this.goodsDao.queryAllGoodsByShopId("001"));
    }

    @Test
    public void queryAllGoods() {
        this.goodsDao.queryAllGoods().forEach((a) -> {
            a.forEach((key, value) -> System.out.println(key + ":" + value));
        });
    }

    @Test
    public void queryGoodsById() {
        System.out.println(this.goodsDao.queryGoodsById(001));
    }

    @Test
    public void queryGoodsByName() {
        System.out.println(this.goodsDao.queryGoodsByName("RRRRR"));
    }

    @Test
    public void deleteById() {
        System.out.println(this.goodsDao.deleteById(7));
    }

}