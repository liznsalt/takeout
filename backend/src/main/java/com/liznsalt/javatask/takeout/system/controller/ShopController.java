package com.liznsalt.javatask.takeout.system.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.liznsalt.javatask.takeout.common.annotation.Log;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.common.dao.UtilDao;
import com.liznsalt.javatask.takeout.system.model.goods.Goods;
import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.goods.GoodsService;
import com.liznsalt.javatask.takeout.system.service.goods.OrderHistoryService;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import com.liznsalt.javatask.takeout.system.service.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private UtilDao utilDao;

    @JsonView(Shop.ShopView.class)
    @RequestMapping("/shop/getAllShopInfo")
    @ResponseBody
    public ResponseBean getAllShopInfo() {
        List<Map<String, Object>> shopList = this.shopService.queryAllShops();
        if (shopList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(shopList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Find Shop")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/shop/getAllGoodsBySid")
    @ResponseBody
    public ResponseBean getAllGoodsBySid(
            @RequestParam("SID") int SID
    ) throws Exception {
        // 先判断商家是否存在
        if (shopService.queryShopById(SID) == null) {
            throw new UnauthorizedException("没有此商家啦!");
        }

        List<Goods> goodsList = this.goodsService.queryAllGoodsByShopId(SID);
        if (goodsList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(goodsList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not find Goods")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/shop/deleteGoodsByGid")
    @ResponseBody
    public ResponseBean deleteGoodsByGid(int GID) throws Exception {
        int res = goodsService.deleteById(GID);
        if (res == 0) {
            throw new UnauthorizedException("此菜单删除会涉及用户历史记录，请联系管理员");
        }
        return new ResponseBean().code(200)
                .message("SUCCESS")
                .data(1);
    }

    @RequestMapping("/shop/updateGoods")
    @ResponseBody
    public ResponseBean updateGoods(
            int GID,
            String name,
            double price,
            int stock,
            String type,
            String desc,
            String url
    ) throws Exception {
        Goods goods = goodsService.queryGoodsById(GID);
        if (goods == null) {
            throw new UnauthorizedException("此商品出现了一些问题，请刷新系统或联系管理员");
        }
        goods.setName(name);
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setType(type);
        goods.setDesc(desc);
        goods.setPictureUrl(url);

        int res = goodsService.update(goods);
        if (res == 0) {
            throw new UnauthorizedException("修改失败，请联系管理员");
        }
        return new ResponseBean().code(200).message("SUCCESS").data(goods);
    }

    @RequestMapping("/shop/addGoods")
    @ResponseBody
    public ResponseBean addGoods(
            int SID, String name, double price,
            int stock, String type, String desc, String url
    ) throws Exception {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setType(type);
        goods.setDesc(desc);
        goods.setPictureUrl(url);
        goods.setSid(SID);

        // 默认值
        goods.setBuyCount(0);
        goods.setPraise(0.0);

        int res = goodsService.add(goods);
        if (res == 0) {
            throw new UnauthorizedException("添加失败，请联系管理员");
        }

        Integer id = utilDao.getLastId();
        goods.setId(id);

        return new ResponseBean().code(200).message("SUCCESS").data(goods);
    }

    @Log("商家接单")
    @RequestMapping("/shop/confirmOrder")
    @ResponseBody
    public boolean confirmOrder(Integer OID) throws Exception {
        OrderHistory orderHistory = orderHistoryService.queryOrderHistoryByOid(OID);
        if (orderHistory == null) {
            throw new UnauthorizedException("查不到啦");
        }
        orderHistory.setState("待评价");

        // 更新库存
        // 更新购买数量
        Goods goods = this.goodsService.queryGoodsById(orderHistory.getGid());
        if (goods.getStock() < orderHistory.getNum()) {
            throw new UnauthorizedException("你家库存不够啦~");
        }
        goods.setStock(goods.getStock() - orderHistory.getNum());
        goods.setBuyCount(goods.getBuyCount() + orderHistory.getNum());
        int res1 = this.goodsService.update(goods);
        if (res1 == 0) {
            throw new UnauthorizedException("更新失败");
        }

        int res2 = orderHistoryService.update(orderHistory);
        if (res2 > 0) {
            return true;
        }
        throw new UnauthorizedException("更新失败");
    }

    @Log("商家拒绝接单")
    @RequestMapping("/shop/cancelOrder")
    @ResponseBody
    public boolean cancelOrder(Integer OID) {
        OrderHistory orderHistory = this.orderHistoryService.queryOrderHistoryByOid(OID);
        orderHistory.setState("已取消");

        int res = this.orderHistoryService.update(orderHistory);
        return res != 0;
    }

    @JsonView(Consumer.UserView.class)
    @RequestMapping("/shop/queryAllConsumers")
    @ResponseBody
    public ResponseBean queryAllConsumers() {
        List<Map<String, Object>> allConsumer = this.consumerService.queryAllConsumer();
        if(allConsumer != null && allConsumer.size() > 0) {
            return new ResponseBean().code(200).message("no，你的数据")
                    .data(allConsumer);
        } else {
            return new ResponseBean().code(200).message("还没有数据啦")
                    .data(new ArrayList<>());
        }
    }
}
