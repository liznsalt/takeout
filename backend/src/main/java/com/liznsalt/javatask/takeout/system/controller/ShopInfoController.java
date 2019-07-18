package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.common.authentication.JWTUtils;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.common.utils.MD5Util;
import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.goods.OrderHistoryService;
import com.liznsalt.javatask.takeout.system.service.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShopInfoController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @RequestMapping("/shop/getInfoBySid")
    @ResponseBody
    public ResponseBean getShopInfoBySid(int SID) {
        Shop shop = shopService.queryShopById(SID);
        if (shop != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(shop);
        } else {
            return new ResponseBean().code(200)
                    .message("没有此商家")
                    .data(null);
        }
    }

    @RequestMapping("/shop/getOrderHistory")
    @ResponseBody
    public ResponseBean getOrderHistoryBySid(int SID) {
        List<OrderHistory> orderHistoryList = this.orderHistoryService
                .queryAllOrderHistoryBySid(SID);
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

    @RequestMapping("/shop/changePassword")
    @ResponseBody
    public ResponseBean shopUpdatePassword(
            int SID,
            String oldPassword,
            String newPassword
    ) throws Exception {
        Shop shop = this.shopService.queryShopById(SID);
        if (shop.getPassword().equals(MD5Util.encrypt(shop.getUsername(), oldPassword))) {
            shop.setPassword(MD5Util.encrypt(shop.getUsername(), newPassword));
            if (shopService.update(shop) != 0) {
                return new ResponseBean().code(200)
                        .message("修改密码成功")
                        .data(JWTUtils.shopSign(shop, MD5Util.encrypt(shop.getUsername(), newPassword)))
                        .put("info", shop);
            } else {
                throw new UnauthorizedException("修改密码失败，请联系管理员");
            }
        } else {
            throw new UnauthorizedException("旧密码错误");
        }
    }

    @RequestMapping("/shop/editInfo")
    @ResponseBody
    public ResponseBean shopEditInfo(
        int SID,
        String name,
        String telephone,
        String address,
        String managerName,
        String managerEmail
    ) throws Exception {
        Shop shop = shopService.queryShopById(SID);
        if (shop == null) {
            throw new UnauthorizedException("你的账号出现了一些问题，请联系管理员");
        }
        if (!shop.getTelephone().equals(telephone) && this.shopService.queryShopByPhone(telephone) != null) {
            throw new UnauthorizedException("这个手机号有人注册过啦~");
        }
        shop.setUsername(name);
        shop.setTelephone(telephone);
        shop.setAddress(address);
        shop.setManagerName(managerName);
        shop.setManagerEmail(managerEmail);

        int res = this.shopService.update(shop);
        if (res == 0) {
            throw new UnauthorizedException("修改失败，请联系管理员");
        }
        return new ResponseBean().code(200)
                .message("SUCCESS")
                .data(shop);
    }

    @RequestMapping("/shop/changePasswordByPhoneCode")
    @ResponseBody
    public boolean changePasswordByPhoneCode(String telephone, String password) {
        Shop shop = this.shopService.queryShopByPhone(telephone);
        shop.setPassword(MD5Util.encrypt(shop.getUsername(), password));
        int res = this.shopService.update(shop);
        return res != 0;
    }
}
