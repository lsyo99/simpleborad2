package com.example.simpleboard.reply.service;

import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ReplyConverter {

    public ReplyDto toDto(ReplyEntity replyEntity){

        return ReplyDto.builder()
                .id(replyEntity.getId())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .status(replyEntity.getStatus())
                .password(replyEntity.getPassword())
                .userName(replyEntity.getUserName())
                .postId(replyEntity.getPost().getId())
                .replied_at(replyEntity.getReplied_at())
                .build();

    }
}
