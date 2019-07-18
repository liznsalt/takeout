package com.liznsalt.javatask.takeout.system.service.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.CommentaryDao;
import com.liznsalt.javatask.takeout.system.model.communication.Commentary;
import com.liznsalt.javatask.takeout.system.service.communication.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentaryService")
public class CommentaryServiceImpl implements CommentaryService {

    @Autowired
    private CommentaryDao commentaryDao;

    @Override
    public int add(Commentary commentary) {
        return this.commentaryDao.add(commentary);
    }

    @Override
    public int deleteCommentaryById(Integer coid) {
        return this.commentaryDao.deleteCommentaryById(coid);
    }

    @Override
    public int update(Commentary commentary) {
        return this.commentaryDao.update(commentary);
    }

    @Override
    public List<Commentary> queryCommentaryBySID(Integer SID) {
        return this.commentaryDao.queryCommentaryBySID(SID);
    }

    @Override
    public Commentary queryCommentaryByCoID(Integer CoID) {
        return this.commentaryDao.queryCommentaryByCoID(CoID);
    }
}
