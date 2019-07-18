package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.common.annotation.Log;
import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.authentication.JWTUtils;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.common.utils.MD5Util;
import com.liznsalt.javatask.takeout.system.model.user.Admin;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import com.liznsalt.javatask.takeout.system.service.user.AdminService;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import com.liznsalt.javatask.takeout.system.service.user.ShopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@Validated
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @Autowired
    private ConsumerService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AdminService adminService;

//    @Autowired
//    public void setService(ConsumerService userService) {
//        this.userService = userService;
//    }

    @Log("用户通过密码登录")
    @PostMapping("/consumer/login")
    @ResponseBody
    public ResponseBean consumerLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) throws Exception {
        Consumer userBean = userService.queryConsumerByName(username);
        if (userBean == null) {
            throw new UnauthorizedException("没有此用户");
        }

        password = MD5Util.encrypt(username, password);

        if (userBean.getPassword().equals(password)) {
            ResponseBean responseBean = new ResponseBean();
            responseBean = responseBean.message("Login success").data(JWTUtils.consumerSign(userBean, password));
            responseBean.put("code", 200);
            responseBean.put("info", userBean);
            return responseBean;
        } else {
            throw new UnauthorizedException("密码错误");
        }
    }

    @Log("用户通过手机验证码登录")
    @PostMapping("/consumer/loginWithoutPass")
    @ResponseBody
    public ResponseBean consumerLoginWithoutPass(
            @Pattern(regexp = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$", message = "{mobile}")
            @RequestParam("telephone") String telephone
    ) throws Exception {
        Consumer userBean = userService.queryConsumerByPhone(telephone);

        if (userBean == null) {
            throw new UnauthorizedException("没有此用户");
        }

        ResponseBean responseBean = new ResponseBean();
        responseBean = responseBean.message("Login success").data(JWTUtils.consumerSign(userBean, userBean.getPassword()));
        responseBean.put("code", 200);
        responseBean.put("info", userBean);
        return responseBean;
    }

    @Log("商家通过手机验证码登录")
    @PostMapping("/shop/loginWithoutPass")
    @ResponseBody
    public ResponseBean shopLoginWithoutPass(@RequestParam("telephone") String telephone) throws Exception {
        Shop userBean = shopService.queryShopByPhone(telephone);

        if (userBean == null) {
            throw new UnauthorizedException("没有此商家");
        }

        ResponseBean responseBean = new ResponseBean();
        responseBean = responseBean.message("Login success").data(JWTUtils.shopSign(userBean, userBean.getPassword()));
        responseBean.put("code", 200);
        responseBean.put("info", userBean);
        return responseBean;
    }

    // shop login
    @Log("商家通过密码登录")
    @PostMapping("/shop/login")
    @ResponseBody
    public ResponseBean shopLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) throws Exception {
        Shop userBean = shopService.queryShopByName(username);

        password = MD5Util.encrypt(username, password);

        if (userBean == null) {
            throw new UnauthorizedException("没有此用户");
        }
        if (userBean.getPassword().equals(password)) {
            ResponseBean responseBean = new ResponseBean();
            responseBean = responseBean
                    .message("Login success")
                    .data(JWTUtils.shopSign(userBean, password));
            responseBean.put("code", 200);
            responseBean.put("info", userBean);
            return responseBean;
        } else {
            throw new UnauthorizedException("密码错误");
        }
    }

    // admin login
    @Log("管理员通过密码登录")
    @PostMapping("/admin/login")
    @ResponseBody
    public ResponseBean adminLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) throws Exception {
        Admin userBean = adminService.queryAdminByName(username);

        password = MD5Util.encrypt(username, password);

        if (userBean == null) {
            throw new UnauthorizedException("没有此管理员");
        }
        if (userBean.getPassword().equals(password)) {
            ResponseBean responseBean = new ResponseBean();
            responseBean = responseBean
                    .message("Login success")
                    .data(JWTUtils.adminSign(userBean, password));
            responseBean.put("code", 200);
            responseBean.put("info", userBean);
            return responseBean;
        } else {
            throw new UnauthorizedException("密码错误");
        }
    }
}