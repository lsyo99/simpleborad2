package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagenation;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService{
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;
    //댓글 보여주기 위해서 주입
    private final ReplyService replyService;
    //create post
    public PostDto create(
            PostRequest postRequest


    ){
        var boardentity = boardRepository.findById(postRequest.getBoradId()).get();
       var entity =  PostEntity.builder()
                .board(boardentity) //<-임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .content(postRequest.getContent())
                .status("registered")
                .title(postRequest.getTitle())
                .postedAt(LocalDateTime.now())
                .build();
       var postentity =postRepository.save(entity);
        return postConverter.toDtoreply(postentity);
    }

    public PostDto view(PostViewRequest postViewRequest) {
        //계시글 있는지 체크
        var postentity =postRepository.findFirstByIdAndStatusOrderById(postViewRequest.getPostId(),"registered")
                // 비밀번호 있는가 체크
                .map(it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword()))
                    {
                        var format = "패스워드가 맞지 않는다 %s %s";
                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword() ));
                    }
                    return it;

                }).orElseThrow(()->{
                    return new RuntimeException("게시글 존재하지 않음"+postViewRequest.getPostId());
                });
        return postConverter.toDto(postentity);
    }
    public Api<List<PostDto>> all(Pageable pageable){
        var postEntity = postRepository.findAll(pageable);
        var pagenation = Pagenation.builder()
                .page(postEntity.getNumber())
                .currentElements(postEntity.getNumberOfElements())
                .size(postEntity.getSize())
                .totalElements(postEntity.getTotalElements())
                .totalPage(postEntity.getTotalPages())
                .build();

      var todto = postEntity.stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());
      var response = Api.<List<PostDto>>builder()
              .body(todto.stream().toList())
              .pagenation(pagenation)
              .build();
      return response;
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "비밀번호가 맞지않습니다.%s %s";
                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword()));
                    }
                    it.setStatus("unregistered");
                    postRepository.save(it);
                    return it;
                }).orElseThrow(()->{
                    return new RuntimeException("게시글 존재하지 않음"+postViewRequest.getPostId());
                });
    }
}
