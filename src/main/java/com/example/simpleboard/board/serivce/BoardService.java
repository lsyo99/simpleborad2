package com.example.simpleboard.board.serivce;

import com.example.simpleboard.board.Model.BoardDto;
import com.example.simpleboard.board.Model.BoardRequest;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;
    public BoardDto create(
            BoardRequest boardRequest){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("Registerd")
                .build();
        return boardConverter.toDto(boardRepository.save(entity));

    }


    public BoardDto view(Long id) {
        var entity = boardRepository.findById(id).get();
        return boardConverter.toDto(entity);
    }
}
