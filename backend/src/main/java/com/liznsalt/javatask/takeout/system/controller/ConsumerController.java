package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.common.annotation.Log;
import com.liznsalt.javatask.takeout.common.authentication.JWTUtils;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.common.utils.MD5Util;
import com.liznsalt.javatask.takeout.system.model.communication.Commentary;
import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;
import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.service.communication.CommentaryService;
import com.liznsalt.javatask.takeout.system.service.goods.OrderHistoryService;
import com.liznsalt.javatask.takeout.system.service.goods.ShoppingCartService;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.Array;
import java.util.*;

@RestController
@Validated
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CommentaryService commentaryService;

    @RequestMapping("/consumer/getInfoByCid")
    @ResponseBody
    public ResponseBean getConsumerInfoByCID(@RequestParam("CID") int CID) {
        Consumer consumer = this.consumerService.queryConsumerById(CID);
        if(consumer != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(consumer);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found!")
                    .data(null);
        }

    }

    @RequestMapping("/consumer/getInfoByCname")
    @ResponseBody
    public ResponseBean getConsumerInfoByName(@RequestParam("CName") String CName) {
        Consumer consumer = this.consumerService.queryConsumerByName(CName);
        if(consumer != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(consumer);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found!")
                    .data(null);
        }
    }

    @RequestMapping("/consumer/getOrderHistory")
    @ResponseBody
    public ResponseBean getOrderHistoryByCID(@RequestParam("CID") int CID) {
        List<OrderHistory> orderHistoryList = this.orderHistoryService
                .queryAllOrderHistoryByCid(CID);
        if (orderHistoryList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(orderHistoryList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Order History")
                    .data(new ArrayList<>());
        }
    }

    @PostMapping("/consumer/changePassword")
    @ResponseBody
    public ResponseBean updatePassword(@RequestParam("CID") int CID,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) throws Exception {
        Consumer consumer = this.consumerService.queryConsumerById(CID);
        if (consumer.getPassword().equals(MD5Util.encrypt(consumer.getUsername(), oldPassword))) {
            consumer.setPassword(MD5Util.encrypt(consumer.getUsername(), newPassword));
            if (consumerService.update(consumer) != 0) {
                return new ResponseBean().code(200)
                        .message("修改密码成功")
                        .data(JWTUtils.consumerSign(consumer, MD5Util.encrypt(consumer.getUsername(), newPassword)))
                        .put("info", consumer);
            } else {
                throw new UnauthorizedException("修改密码失败，请联系管理员");
            }
        } else {
            throw new UnauthorizedException("旧密码错误");
        }
    }

    @RequestMapping("/consumer/getShoppingCartByCid")
    @ResponseBody
    public ResponseBean getShoppingCartByCID(@RequestParam("CID") int CID) {
        List<ShoppingCart> shoppingCartList = this.shoppingCartService
                .queryShoppingCartByCID(CID);
        if (shoppingCartList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(shoppingCartList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Relevant ShoppingCart")
                    .data(new ArrayList<> ());
        }
    }

    @RequestMapping("/consumer/editInfo")
    @ResponseBody
    public ResponseBean editConsumerInfo(
            Integer CID,
            @NotBlank(message = "{required}") String name,
            String sex,
            Integer age,
            String telephone,
            @Email(message = "{email}") String email,
            String address
    ) throws Exception {
        Consumer consumer = this.consumerService.queryConsumerById(CID);
        if (consumer == null) {
            throw new UnauthorizedException("您的账号出现的一些问题，请联系管理员");
        }
        if (age <= 0) {
            throw new UnauthorizedException("本系统不支持刚出生或者还在母胎中的人使用哟~");
        }
        if (!consumer.getTelephone().equals(telephone) && this.consumerService.queryConsumerByPhone(telephone) != null) {
            throw new UnauthorizedException("这个手机号有人注册过啦~");
        }
        consumer.setUsername(name);
        consumer.setSex(sex);
        consumer.setAge(age);
        consumer.setTelephone(telephone);
        consumer.setEmail(email);
        consumer.setAddress(address);

        int res = this.consumerService.update(consumer);
        if (res == 0) {
            throw new UnauthorizedException("修改失败，请联系管理员");
        }
        return new ResponseBean().code(200)
                .message("SUCCESS")
                .data(consumer);
    }

    @RequestMapping("/consumer/changePasswordByPhoneCode")
    @ResponseBody
    public boolean changePasswordByPhoneCode(String telephone, String password) {
        Consumer consumer = this.consumerService.queryConsumerByPhone(telephone);
        consumer.setPassword(MD5Util.encrypt(consumer.getUsername(), password));
        int res = this.consumerService.update(consumer);
        return res != 0;
    }

    @RequestMapping("/consumer/clearShoppingCart")
    @ResponseBody
    public boolean clearShoppingCart(Integer CID, Integer SID) {
        int res = this.shoppingCartService.clearShoppingCartByCidSid(CID, SID);
        return res != 0;
    }

    @RequestMapping("/consumer/getShoppingCartByCidSid")
    @ResponseBody
    public ResponseBean getShoppingCartByCIDSID(int CID, int SID) {
        List<ShoppingCart> shoppingCartList = this.shoppingCartService
                .queryShoppingCartByCIDSID(CID, SID);
        if (shoppingCartList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(shoppingCartList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Relevant ShoppingCart")
                    .data(new ArrayList<> ());
        }
    }

    @Log("用户评论")
    @RequestMapping("/consumer/commentTo")
    @ResponseBody
    public boolean commentTo(
            Integer CID,
            Integer SID,
            String content,
            int stars,
            Date date
    ) {
        Commentary commentary = new Commentary();
        commentary.setCid(CID);
        commentary.setSid(SID);
        commentary.setContent(content);
        commentary.setStars(stars);
        commentary.setTime(date);

        int res = this.commentaryService.add(commentary);
        return res != 0;
    }

    @Log("用户取消订单")
    @RequestMapping("/consumer/cancelOrder")
    @ResponseBody
    public boolean cancelOrder(Integer OID) {
        OrderHistory orderHistory = this.orderHistoryService.queryOrderHistoryByOid(OID);
        orderHistory.setState("已取消");

        int res = this.orderHistoryService.update(orderHistory);
        return res != 0;
    }

    @RequestMapping("/consumer/getOrderByTime")
    @ResponseBody
    public Map<Date, List<OrderHistory>> getOrderByTime(Integer CID) {
        List<OrderHistory> orderHistoryList = this.orderHistoryService.queryAllOrderHistoryByCid(CID);
        Map<Date, List<OrderHistory>> map = new HashMap<>();
        for (OrderHistory orderHistory : orderHistoryList) {
            if (!map.containsKey(orderHistory.getTime())) {
                map.put(orderHistory.getTime(), new ArrayList<>());
            }
            ((List<OrderHistory>)map.get(orderHistory.getTime())).add(orderHistory);
        }
        return map;
    }
}
