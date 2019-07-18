package com.liznsalt.javatask.takeout.system.service.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.ShopReplyDao;
import com.liznsalt.javatask.takeout.system.model.communication.ShopReply;
import com.liznsalt.javatask.takeout.system.service.communication.ShopReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("shopReplyService")
public class ShopReplyServiceImpl implements ShopReplyService {

    @Autowired
    private ShopReplyDao shopReplyDao;

    @Override
    public int add(ShopReply shopReply) {
        return this.shopReplyDao.add(shopReply);
    }

    @Override
    public int delete(Integer sid, Integer coid, Date date) {
        return this.shopReplyDao.delete(sid, coid, date);
    }

    @Override
    public int update(ShopReply oldReply, ShopReply newReply) {
        return this.shopReplyDao.update(oldReply, newReply);
    }

    @Override
    public List<ShopReply> queryShopReplyByCoID(Integer CoID) {
        return this.shopReplyDao.queryShopReplyByCoID(CoID);
    }

    @Override
    public List<ShopReply> queryShopReplyBySID(Integer SID) {
        return this.shopReplyDao.queryShopReplyBySID(SID);
    }
}
