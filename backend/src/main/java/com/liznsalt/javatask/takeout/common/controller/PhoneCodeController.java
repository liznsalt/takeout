package com.liznsalt.javatask.takeout.common.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.liznsalt.javatask.takeout.common.utils.CodeUtil;
import com.liznsalt.javatask.takeout.common.utils.SmsTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PhoneCodeController {

    //    HYT手机号: 13959258709
    /**
     * 发送短信
     * @param phone
     * @param request
     * @return
     * @throws com.aliyuncs.exceptions.ClientException
     */
    @RequestMapping(value = "/sms/smsXxs")
    @ResponseBody
    public Map<String,Object> smsXxs(String phone, HttpServletRequest request) throws ClientException {
        Map<String,Object> map = new HashMap<>();
        // 验证码（指定长度的随机数）
        String code = CodeUtil.generateVerifyCode(6);
        String TemplateParam = "{\"code\":\""+code+"\"}";
//        System.out.println(TemplateParam);
        // 短信模板id
        String TemplateCode = "SMS_169900364";
        SendSmsResponse response = SmsTool.sendSms(phone,TemplateParam,TemplateCode);
        map.put("verifyCode",code);
        map.put("phone",phone);
        request.getSession().setAttribute("CodePhone",map);
        if( response.getCode() != null && response.getCode().equals("OK")) {
            map.put("isOk","OK");
        }
        return map;
    }
}
