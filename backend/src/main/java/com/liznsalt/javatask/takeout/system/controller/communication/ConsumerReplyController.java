package com.liznsalt.javatask.takeout.system.controller.communication;

import com.liznsalt.javatask.takeout.common.bean.ResponseBean;
import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import com.liznsalt.javatask.takeout.system.model.communication.ConsumerReply;
import com.liznsalt.javatask.takeout.system.service.communication.ConsumerReplyService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Validated
@RestController
public class ConsumerReplyController {
    @Autowired
    private ConsumerReplyService consumerReplyService;

    @RequestMapping("/consumerReply/add")
    @ResponseBody
    public ResponseBean addConsumerReply(
            @RequestParam("uid") int cid,
            int coid,
            Date cotime,
            @Length(min = 1, max = 1024, message = "{range}") String content
    ) throws Exception {
        ConsumerReply consumerReply = new ConsumerReply();
        consumerReply.setCid(cid);
        consumerReply.setCoid(coid);
        consumerReply.setTime(cotime);
        consumerReply.setContent(content);
        int res = this.consumerReplyService.add(consumerReply);
        if(res==1){
            return new ResponseBean()
                    .code(200)
                    .message("Success")
                    .data(1);
        }else{
            throw new UnauthorizedException("评论回复失败，请联系管理员");
        }
    }

    @RequestMapping("/consumerReply/delete")
    @ResponseBody
    public ResponseBean deleteConsumerReply(
            @RequestParam("uid") int cid,
            int coid,
            Date date
    ) throws Exception {
        int res = this.consumerReplyService.delete(cid,coid,date);
        if(res==0){
            throw new UnauthorizedException("删除失败，请联系管理员");
        }else{
            return new ResponseBean()
                    .code(200)
                    .message("Success")
                    .data(1);
        }
    }

    @RequestMapping("/consumerReply/getInfoByCoid")
    @ResponseBody
    public ResponseBean getConsumerReplyInfoByCoid(int coid) throws  Exception{
        List<ConsumerReply> consumerReplyList = this.consumerReplyService.queryConsumerReplyByCoID(coid);
        if (consumerReplyList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(consumerReplyList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Order History")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/consumerReply/getInfoByCid")
    @ResponseBody
    public ResponseBean getConsumerReplyInfoByCid(int cid) throws Exception{
        List<ConsumerReply> consumerReplyList = this.consumerReplyService.queryConsumerReplyByCID(cid);
        if (consumerReplyList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(consumerReplyList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not Found Order History")
                    .data(new ArrayList<>());
        }
    }

}
