package com.liznsalt.javatask.takeout.system.controller.communication;

import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.system.model.communication.ShopReply;
import com.liznsalt.javatask.takeout.system.service.communication.ShopReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ShopReplyController {
    @Autowired
    private ShopReplyService shopReplyService;

    @RequestMapping("/shopReply/add")
    @ResponseBody
    public ResponseBean addShopReply(
            @RequestParam("uid") int sid,
            int coid,
            Date cotime,
            String content
    ) throws Exception {
        ShopReply shopReply = new ShopReply();
        shopReply.setSid(sid);
        shopReply.setCoid(coid);
        shopReply.setTime(cotime);
        shopReply.setContent(content);
        int res = this.shopReplyService.add(shopReply);
        if(res==1){
            return new ResponseBean()
                    .code(200)
                    .message("Success")
                    .data(1);
        }else{
            throw new UnauthorizedException("评论回复失败，请联系管理员");
        }
    }

    @RequestMapping("/shopReply/delete")
    @ResponseBody
    public ResponseBean deleteShopReply(
            @RequestParam("uid") int sid,
            int coid,
            Date date
    ) throws Exception {
        int res = this.shopReplyService.delete(sid,coid,date);
        if(res==0){
            throw new UnauthorizedException("删除失败，请联系管理员");
        }else{
            return new ResponseBean()
                    .code(200)
                    .message("Success")
                    .data(1);
        }
    }

    @RequestMapping("/shopReply/getInfoByCoid")
    @ResponseBody
    public ResponseBean getShopReplyInfoByCoid(int coid) throws  Exception{
        List<ShopReply> shopReplyList = this.shopReplyService.queryShopReplyByCoID(coid);
        if (shopReplyList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(shopReplyList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Order History")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/shopReply/getInfoBySid")
    @ResponseBody
    public ResponseBean getShopReplyInfoBySid(int sid) throws Exception{
        List<ShopReply> shopReplyList = this.shopReplyService.queryShopReplyBySID(sid);
        if (shopReplyList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(shopReplyList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Order History")
                    .data(new ArrayList<>());
        }
    }


}
