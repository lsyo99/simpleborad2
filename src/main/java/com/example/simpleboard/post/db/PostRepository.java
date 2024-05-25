package com.example.simpleboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    //view에서 id만이 아닌 해당글이 존재하는지로 확인 query로 생성
    // select * from post where id =? and status=? order by desc limit 1
     Optional<PostEntity> findFirstByIdAndStatusOrderById(Long id, String status);
}
