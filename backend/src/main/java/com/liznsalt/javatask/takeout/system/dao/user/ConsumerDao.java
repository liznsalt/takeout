package com.liznsalt.javatask.takeout.system.dao.user;

import com.liznsalt.javatask.takeout.system.model.user.Consumer;

import java.util.List;
import java.util.Map;

public interface ConsumerDao {
    int add(Consumer consumer); // 注册一个用户
    int update(Consumer consumer); // 更新用户信息
    int deleteById(Integer cid); // 注销账号
    List<Map<String, Object>> queryAllConsumer(); // 所有用户
    Consumer queryConsumerById(Integer cid); // 通过id查询用户
    List<Consumer> queryConsumerByName(String cname); // 通过名字查询用户

    Consumer queryConsumerByPhone(String phone);
}