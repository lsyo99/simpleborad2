package com.example.simpleboard.post.service;

import com.example.simpleboard.board.Model.BoardDto;
import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.service.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostConverter {

    private final ReplyConverter replyConverter;


    public PostDto toDto(PostEntity postEntity){

        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .BoardId(postEntity.getBoard().getId())
                .build();

    }
    public PostDto toDtoreply(PostEntity postEntity){
        var replyList = postEntity.getReplyList().stream()
                .map(replyConverter::toDto).collect(Collectors.toList());
        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .BoardId(postEntity.getBoard().getId())
                .replyList(replyList)
                .build();

    }

}
