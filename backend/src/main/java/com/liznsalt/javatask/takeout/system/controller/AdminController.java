package com.liznsalt.javatask.takeout.system.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.liznsalt.javatask.takeout.common.authentication.JWTUtils;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.common.utils.MD5Util;
import com.liznsalt.javatask.takeout.system.model.user.Admin;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.goods.OrderHistoryService;
import com.liznsalt.javatask.takeout.system.service.user.AdminService;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import com.liznsalt.javatask.takeout.system.service.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private AdminService adminService;

    @JsonView(Consumer.AdminView.class)
    @RequestMapping("/admin/queryAllConsumers")
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

    @JsonView(Shop.AdminView.class)
    @RequestMapping("/admin/queryAllShops")
    @ResponseBody
    public ResponseBean queryAllShops() {
        List<Map<String, Object>> allShops = this.shopService.queryAllShops();
        if(allShops != null && allShops.size() > 0) {
            return new ResponseBean().code(200).message("no，你的数据")
                    .data(allShops);
        } else {
            return new ResponseBean().code(200).message("还没有数据啦")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/admin/updateConsumerByID")
    @ResponseBody
    public ResponseBean updateConsumerByID(Integer CID, String attr, Object value)
            throws Exception {
        Consumer consumer = this.consumerService.queryConsumerById(CID);
        switch (attr){
            case "name": consumer.setUsername((String) value); break;
            case "sex": consumer.setSex((String) value); break;
            case "age": consumer.setAge((Integer) value); break;
            case "tel": consumer.setTelephone((String) value); break;
            case "email": consumer.setEmail((String) value); break;
            case "address": consumer.setAddress((String) value); break;
            case "password": consumer.setPassword((String) value); break;
            case "balance": consumer.setBalance((Double) value); break;
            default : break;
        }
        int res = this.consumerService.update(consumer);
        if (res == 0) {
            throw new UnauthorizedException("没法更啊");
        }
        return new ResponseBean().code(200).message("SUCCESS");
    }

    @RequestMapping("/admin/updateShopByID")
    @ResponseBody
    public ResponseBean updateShopById(Integer SID, String attr, Object value)
            throws Exception {
        Shop shop = this.shopService.queryShopById(SID);
        switch (attr) {
            case "name": shop.setUsername((String) value); break;
            case "password": shop.setPassword((String) value); break;
            case "tel": shop.setTelephone((String) value); break;
            case "address": shop.setAddress((String) value); break;
            case "managerName": shop.setManagerName((String) value); break;
            case "managerEmail": shop.setManagerEmail((String) value); break;
            case "pictureUrl": shop.setPictureUrl((String) value); break;
            case "score": shop.setScore((Double) value); break;
            default: break;
        }
        int res = this.shopService.update(shop);
        if (res == 0) {
            throw new UnauthorizedException("没法更啊");
        } else {
            return new ResponseBean().code(200).message("SUCCESS");
        }
    }

    @RequestMapping("/admin/deleteConsumerByID")
    @ResponseBody
    public ResponseBean deleteConsumerByID(Integer CID) throws Exception {
        int res = this.consumerService.deleteById(CID);
        if (res == 0) {
            throw new UnauthorizedException("删不掉啦，没有这个ID啦");
        } else {
            return new ResponseBean().code(200).message("SUCCESS");
        }
    }

    @RequestMapping("/admin/deleteShopByID")
    @ResponseBody
    public ResponseBean deleteShopByID(Integer SID) throws Exception {
        int res = this.shopService.deleteById(SID);
        if (res == 0) {
            throw new UnauthorizedException("删不掉啦，没有这个ID啦");
        } else {
            return new ResponseBean().code(200).message("SUCCESS");
        }
    }

    @RequestMapping("/admin/updateConsumerByCidWithAllParams")
    @ResponseBody
    public ResponseBean updateConsumerByCidWithAllParams(
        Integer CID,
        String name,
        String sex,
        Integer age,
        String telephone,
        String email,
        String address,
        String password,
        Double balance
    ) throws Exception {
        Consumer consumer = consumerService.queryConsumerById(CID);
        consumer.setUsername(name);
        consumer.setSex(sex);
        consumer.setAge(age);
        consumer.setTelephone(telephone);
        consumer.setEmail(email);
        consumer.setAddress(address);
        consumer.setPassword(password);
        consumer.setBalance(balance);

        int res = this.consumerService.update(consumer);
        if (res == 0) {
            throw new UnauthorizedException("没法更啊");
        }
        return new ResponseBean().code(200).message("SUCCESS");
    }

    @RequestMapping("/admin/updateShopBySidWithAllParams")
    @ResponseBody
    public ResponseBean updateShopBySidWithAllParams(
        Integer SID,
        String name,
        String password,
        String telephone,
        String address,
        String managerName,
        String managerEmail,
        String pictureUrl,
        Double score
    ) throws Exception {
        Shop shop = shopService.queryShopById(SID);
        shop.setUsername(name);
        shop.setPassword(password);
        shop.setTelephone(telephone);
        shop.setAddress(address);
        shop.setManagerName(managerName);
        shop.setManagerEmail(managerEmail);
        shop.setPictureUrl(pictureUrl);
        shop.setScore(score);

        int res = this.shopService.update(shop);
        if (res == 0) {
            throw new UnauthorizedException("没法更啊");
        } else {
            return new ResponseBean().code(200).message("SUCCESS");
        }
    }

    @RequestMapping("/admin/getAllOrderHistory")
    @ResponseBody
    public List<Map<String, Object>> getAllOrderHistory() {
        return this.orderHistoryService.queryAllOrderHistory();
    }

    @PostMapping("/admin/changePassword")
    @ResponseBody
    public ResponseBean updatePassword(@RequestParam("AID") int AID,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) throws Exception {
        Admin admin = this.adminService.queryAdminById(AID);
        if (admin.getPassword().equals(MD5Util.encrypt(admin.getUsername(), oldPassword))) {
            admin.setPassword(MD5Util.encrypt(admin.getUsername(), newPassword));
            if (adminService.update(admin) != 0) {
                return new ResponseBean().code(200)
                        .message("修改密码成功")
                        .data(JWTUtils.adminSign(admin, MD5Util.encrypt(admin.getUsername(), newPassword)))
                        .put("info", admin);
            } else {
                throw new UnauthorizedException("修改密码失败，请联系管理员");
            }
        } else {
            throw new UnauthorizedException("旧密码错误");
        }
    }
}
