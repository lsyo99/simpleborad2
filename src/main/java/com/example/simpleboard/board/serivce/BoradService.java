package com.example.simpleboard.board.serivce;

import com.example.simpleboard.board.Model.BoardRequest;

import com.example.simpleboard.board.db.BoradEntity;
import com.example.simpleboard.board.db.BoradRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoradService {
    private final BoradRepository boradRepository;
    public BoradEntity create(
            BoardRequest boardRequest){
        var entity = BoradEntity.builder()
                .boradName(boardRequest.getBoardName())
                .status("Registerd")
                .build();
        return boradRepository.save(entity);

    }


}
