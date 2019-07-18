package com.liznsalt.javatask.takeout.system.service.goods.impl;

import com.liznsalt.javatask.takeout.system.dao.goods.GoodsDao;
import com.liznsalt.javatask.takeout.system.model.goods.Goods;
import com.liznsalt.javatask.takeout.system.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public int add(Goods goods) {
        return this.goodsDao.add(goods);
    }

    @Override
    public int update(Goods goods) { return this.goodsDao.update(goods); }

    @Override
    public int deleteById(Integer gid) { return this.goodsDao.deleteById(gid) ; }

    @Override
    public List<Goods> queryAllGoodsByShopId(Integer sid) { return this.goodsDao.queryAllGoodsByShopId(sid);}

    @Override
    public List<Map<String, Object>> queryAllGoods() { return this.goodsDao.queryAllGoods(); }

    @Override
    public Goods queryGoodsById(Integer gid) { return this.goodsDao.queryGoodsById(gid); }

    @Override
    public List<Map<String, Object>> queryGoodsByName(String gname) {
        return this.goodsDao.queryGoodsByName(gname);
    }
}
