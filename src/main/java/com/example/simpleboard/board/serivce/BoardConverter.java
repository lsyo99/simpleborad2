package com.example.simpleboard.board.serivce;

import com.example.simpleboard.board.Model.BoardDto;
import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BoardConverter {
    private final PostConverter postConverter;
    public BoardDto toDto(BoardEntity boardEntity){
//       var postList = boardEntity.getPostList().stream()
//                .map(postEntity -> {
//                    return postConverter.toDto(postEntity);
//                }).collect(Collectors.toList());
        var postList = boardEntity.getPostList().stream()
                        .map(postConverter::toDto).collect(Collectors.toList());
        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                //boardentity에서 mappedby가 되어있기때문에 N개의 데이터를 가지고 온다.
                // 그렇기 때문에 postconverter참조
                .postList(postList)
                .build();
    }
}
