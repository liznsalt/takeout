package com.liznsalt.javatask.takeout.system.dao.communication;

import com.liznsalt.javatask.takeout.system.model.communication.Commentary;

import java.util.List;

public interface CommentaryDao {
    int add(Commentary commentary); // 添加评论
    int deleteCommentaryById(Integer coid); // 通过评论ID删除, 并删除此评论下的所有回复
    int update(Commentary commentary); // 修改评论
    List<Commentary> queryCommentaryBySID(Integer SID);
    Commentary queryCommentaryByCoID(Integer CoID);
}
