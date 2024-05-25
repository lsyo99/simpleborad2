package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService{
    private final PostRepository postRepository;
    //댓글 보여주기 위해서 주입
    private final ReplyService replyService;
    //create post
    public PostEntity create(
            PostRequest postRequest
    ){
       var entity =  PostEntity.builder()
                .boradId(1L) //<-임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .content(postRequest.getContent())
                .status("registered")
                .title(postRequest.getTitle())
                .postedAt(LocalDateTime.now())
                .build();
        return postRepository.save(entity);
    }

    public PostEntity view(PostViewRequest postViewRequest) {
        //계시글 있는지 체크
        return postRepository.findFirstByIdAndStatusOrderById(postViewRequest.getPostId(),"registered")
                // 비밀번호 있는가 체크
                .map(it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword()))
                    {
                        var format = "패스워드가 맞지 않는다 %s %s";
                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword() ));
                    }
                    var replyList = replyService.findAllPostId(it.getId());
                    it.setReplyList(replyList);
                    return it;

                }).orElseThrow(()->{
                    return new RuntimeException("게시글 존재하지 않음"+postViewRequest.getPostId());
                });
    }
    public List<PostEntity> all(){
        return postRepository.findAll();
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
