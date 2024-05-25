package com.example.simpleboard.board.controller;

import com.example.simpleboard.board.Model.BoardRequest;

import com.example.simpleboard.board.db.BoradEntity;
import com.example.simpleboard.board.serivce.BoradService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {
    // 기본생성자에 해당값이 들어가서 생성이 된다.
    private final BoradService boradService;
    @PostMapping("")
    public BoradEntity create(
            @Valid
            @RequestBody BoardRequest boardRequest
    ){
        return boradService.create(boardRequest);
    }

}
