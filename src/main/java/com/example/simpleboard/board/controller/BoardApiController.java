package com.example.simpleboard.board.controller;

import com.example.simpleboard.board.Model.BoardDto;
import com.example.simpleboard.board.Model.BoardRequest;

import com.example.simpleboard.board.db.BoardEntity;

import com.example.simpleboard.board.serivce.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {
    // 기본생성자에 해당값이 들어가서 생성이 된다.
    private final BoardService boardService;
    @PostMapping("")
    public BoardDto create(
            @Valid
            @RequestBody BoardRequest boardRequest
    ){
        return boardService.create(boardRequest);
    }
    @GetMapping("/id/{id}")
    public BoardDto view(@PathVariable Long id){
        return boardService.view(id);
    }

}
