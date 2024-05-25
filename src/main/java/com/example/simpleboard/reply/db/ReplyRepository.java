package com.example.simpleboard.reply.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {
    // reply테이블에서 postid가 ?인것 찾기위해 -> 창을 열었을때 해당 댓글이 보이도록
    // select * from reply where post_id = ? and status =? order by id desc
    List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(Long postId,String status);
}
