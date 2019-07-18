package com.liznsalt.javatask.takeout.system.service.communication;

import com.liznsalt.javatask.takeout.system.model.communication.ShopReply;

import java.util.Date;
import java.util.List;

public interface ShopReplyService {
    int add(ShopReply shopReply); // 添加回复
    int delete(Integer sid, Integer coid, Date date); // 通过店家ID，评论ID和评论时间删除回复
    // 因为回复没有主码ID，所以需要知道之前的回复
    int update(ShopReply oldReply, ShopReply newReply);
    List<ShopReply> queryShopReplyByCoID(Integer CoID);
    List<ShopReply> queryShopReplyBySID(Integer SID);
}
