package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.common.annotation.Log;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.system.model.goods.Goods;
import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;
import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.goods.GoodsService;
import com.liznsalt.javatask.takeout.system.service.goods.OrderHistoryService;
import com.liznsalt.javatask.takeout.system.service.goods.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BuyController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @RequestMapping("/ShoppingCart/add")
    @ResponseBody
    public ResponseBean addShoppingCart(Integer CID, Integer GID) throws Exception {
        Goods good = goodsService.queryGoodsById(GID);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCid(CID);
        shoppingCart.setGid(GID);
        shoppingCart.setNum(1);
        shoppingCart.setTotal(good.getPrice());
        int i = this.shoppingCartService.addShoppingCartItem(shoppingCart);
        if (i != 0) {
            return new ResponseBean().code(200).message("SUCCESS").data(shoppingCart);
        } else {
            throw new UnauthorizedException("Add ERROR");
        }
    }

    @RequestMapping("/ShoppingCart/numAdd")
    @ResponseBody
    public ResponseBean numAdd(Integer CID, Integer GID) throws Exception {
        ShoppingCart shoppingCart = shoppingCartService.queryShoppingCartByID(CID, GID);
        if (shoppingCart == null) {
            throw new UnauthorizedException("NO this shoppingcart");
        } else {
            shoppingCart.setNum(shoppingCart.getNum() + 1);
            shoppingCart.setTotal(shoppingCart.getPrice() * shoppingCart.getNum());
            int i = this.shoppingCartService.updateShoppingCartItem(shoppingCart);
            if (i == 0) {
                throw new UnauthorizedException("更新出错啦");
            } else {
                return new ResponseBean().code(200).message("OJBK").data(shoppingCart);
            }
        }
    }

    @RequestMapping("/ShoppingCart/numSub")
    @ResponseBody
    public ResponseBean numSub(Integer CID, Integer GID) throws Exception {
        ShoppingCart shoppingCart = shoppingCartService.queryShoppingCartByID(CID, GID);
        if (shoppingCart == null) {
            throw new UnauthorizedException("NO this shoppingcart");
        } else {
            shoppingCart.setNum(shoppingCart.getNum() - 1);
            shoppingCart.setTotal(shoppingCart.getPrice() * shoppingCart.getNum());
            int i = this.shoppingCartService.updateShoppingCartItem(shoppingCart);
            if (i == 0) {
                throw new UnauthorizedException("更新出错啦");
            }
            return new ResponseBean().code(200).message("OJBK").data(shoppingCart);
        }
    }

    @RequestMapping("/ShoppingCart/numChoose")
    @ResponseBody
    public ResponseBean numChoose(Integer CID, Integer GID, int num) throws Exception {
        ShoppingCart shoppingCart = shoppingCartService.queryShoppingCartByID(CID, GID);
        if (shoppingCart == null) {
            throw new UnauthorizedException("NO this shoppingcart");
        } else {
            if (num < 0) {
                throw new UnauthorizedException("购买数量不能为负值");
            }

            Goods goods = this.goodsService.queryGoodsById(GID);
            if (goods == null) {
                throw new UnauthorizedException("此商品可能被吃完了~");
            }
            if (num > goods.getStock()) {
                throw new UnauthorizedException("不够了啦~");
            }

            shoppingCart.setNum(num);
            shoppingCart.setTotal(shoppingCart.getPrice() * shoppingCart.getNum());
            int i = this.shoppingCartService.updateShoppingCartItem(shoppingCart);
            if (i == 0) {
                throw new UnauthorizedException("更新出错啦");
            }
            return new ResponseBean().code(200).message("OJBK").data(shoppingCart);
        }
    }

    @RequestMapping("/ShoppingCart/deleteItem")
    @ResponseBody
    public ResponseBean deleteItem(Integer CID, Integer GID) throws Exception {
        int i = this.shoppingCartService.deleteShoppingCartItemById(CID, GID);
        if (i == 0) {
            throw new UnauthorizedException("Delete ShoppingCartItem Error.");
        } else {
            return new ResponseBean().code(200).message("Success");
        }
    }

    @RequestMapping("/ShoppingCart/deleteAllItem")
    @ResponseBody
    public ResponseBean deleteAllItem(Integer CID) {
        int i = this.shoppingCartService.clearShoppingCart(CID);
        return new ResponseBean().code(200).message("0v0").data(i);
    }

    @Log("用户购买")
    @RequestMapping("/ShoppingCart/confirmItem")
    @ResponseBody
    public ResponseBean confirmItem(Integer CID, Integer GID) throws Exception{
        ShoppingCart shoppingCart = this.shoppingCartService
                    .queryShoppingCartByID(CID, GID);
        if (shoppingCart == null) {
            throw new UnauthorizedException("Query Shoppingcart Error");
        }
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setCid(CID);
        orderHistory.setGid(GID);
        orderHistory.setState("未完成");
        orderHistory.setNum(shoppingCart.getNum());
        orderHistory.setTotal(shoppingCart.getTotal());
        orderHistory.setTime(new Date());
        int i = this.orderHistoryService.add(orderHistory);
        if (i == 0) {
            throw new UnauthorizedException("Add OrderHistory Error");
        }
        int ii = this.shoppingCartService.deleteShoppingCartItemById(CID, GID);
        if (ii == 0) {
            throw new UnauthorizedException("Delete Error");
        }
        return new ResponseBean().code(200).message("Success");
    }

    @Log("用户购买")
    @RequestMapping("/ShoppingCart/confirmItemWithDate")
    @ResponseBody
    public ResponseBean confirmItemWithDate(Integer CID, Integer GID, Date now) throws Exception {
        ShoppingCart shoppingCart = this.shoppingCartService
                .queryShoppingCartByID(CID, GID);
        if (shoppingCart == null) {
            throw new UnauthorizedException("Query Shoppingcart Error");
        }
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setCid(CID);
        orderHistory.setGid(GID);
        orderHistory.setState("未完成");
        orderHistory.setNum(shoppingCart.getNum());
        orderHistory.setTotal(shoppingCart.getTotal());
        orderHistory.setTime(now);
        int i = this.orderHistoryService.add(orderHistory);
        if (i == 0) {
            throw new UnauthorizedException("Add OrderHistory Error");
        }
        int ii = this.shoppingCartService.deleteShoppingCartItemById(CID, GID);
        if (ii == 0) {
            throw new UnauthorizedException("Delete Error");
        }
        return new ResponseBean().code(200).message("Success");
    }

    @RequestMapping("/ShoppingCart/queryById")
    @ResponseBody
    public ResponseBean queryById(Integer CID, Integer GID) throws Exception {
        ShoppingCart shoppingCart = shoppingCartService.queryShoppingCartByID(CID, GID);
        if (shoppingCart == null) {
            throw new UnauthorizedException("No this shopping cart");
        }
        return new ResponseBean().code(200)
                .message("OK")
                .data(shoppingCart);
    }

    @RequestMapping("/ShoppingCart/finishOrder")
    @ResponseBody
    public void finishOrder(Integer OID) throws Exception {
        OrderHistory orderHistory = this.orderHistoryService.queryOrderHistoryByOid(OID);
        if (OID == null) {
            throw new UnauthorizedException("订单可能不存在了，你刷新一下~");
        }
        orderHistory.setState("已完成");
        if (this.orderHistoryService.update(orderHistory) == 0) {
            throw new UnauthorizedException("哪知道什么错误...");
        }
    }
}
