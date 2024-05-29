package com.example.simpleboard.post.controller;
import com.example.simpleboard.common.Api;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostConverter;
import com.example.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController{
    private final PostService postService;


    //create post
    @PostMapping("")
    public PostDto create(@Valid @RequestBody PostRequest postRequest)
    {
        return postService.create(postRequest);
    }
    //Get이 아닌 이유는 id로 받아야되지만 익명 계시판의 비밀번호를 널어야만 열람할 수 있는 형태이기 때문에
    @PostMapping("/view")
    public PostDto view(
            @Valid
            @RequestBody PostViewRequest postViewRequest
            ){
        return postService.view(postViewRequest);
        }
    //계시글 전페
    @GetMapping("/all")
    public Api<List<PostDto>> list(
            @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC)
            Pageable pageable
    )
    {
        return postService.all(pageable);
    }
    //pathvariable을 넣을수 없다(비밀번호를 받아야되기 때문에)
    @PostMapping("/delete")
    public void delete(@Valid @RequestBody PostViewRequest postViewRequest){
        postService.delete(postViewRequest);
    }

}
