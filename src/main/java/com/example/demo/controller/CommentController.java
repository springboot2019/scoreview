package com.example.demo.controller;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class CommentController {
    /*
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/comment/{score_id}")
    public Comment getCommentByScore_id(@PathVariable("score_id")Integer score_id){
        Comment comment=commentRepository.findCommentByScore_id(score_id);
        if(comment!=null)
            return comment;
        else
            return "暂无评价";
    }
    @GetMapping(value = "/comment/{score_id}")
    public Comment addCommentByScore_id(@PathVariable("score_id")Integer score_id){
        Comment comment=commentRepository.findCommentByScore_id(score_id);
        if(comment!=null)
            return comment;
        else
            return "暂无评价";
    }
    */

}
