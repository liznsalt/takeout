package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.common.annotation.Log;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.utils.MD5Util;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import com.liznsalt.javatask.takeout.system.service.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ConsumerService consumerService;

    @Log("用户注册")
    @PostMapping("/signIn/consumer")
    @ResponseBody
    public ResponseBean addAConsumer(
            String name,
            String password,
            String telephone
    ) {
        if (consumerService.queryConsumerByName(name) != null) {
            return new ResponseBean().code(401)
                    .message("该用户名已有人注册");
        }
        Consumer consumer = new Consumer();
        consumer.setUsername(name);
        consumer.setPassword(MD5Util.encrypt(name, password));
        consumer.setTelephone(telephone);
        consumer.setBalance(0.0);

        int res = consumerService.add(consumer);
        if (res == 0) {
            return new ResponseBean().code(500)
                    .message("出了点错误，请联系管理员");
        }
        return new ResponseBean().code(200)
                .message("SUCCESS");
    }

    @Log("商家入驻")
    @PostMapping("/signIn/shop")
    @ResponseBody
    public ResponseBean addAShop(
        String name,
        String managerName,
        String telephone,
        String address,
        String password
    ) {
        if (shopService.queryShopByName(name) != null) {
            return new ResponseBean().code(401)
                    .message("该用户名已有人注册");
        }
        Shop shop = new Shop();
        shop.setUsername(name);
        shop.setManagerName(managerName);
        shop.setTelephone(telephone);
        shop.setAddress(address);
        shop.setPassword(MD5Util.encrypt(name, password));

        int res = shopService.add(shop);
        if (res == 0) {
            return new ResponseBean().code(500)
                    .message("出了点错误，请联系管理员");
        }
        return new ResponseBean().code(200)
                .message("SUCCESS");
    }

}
