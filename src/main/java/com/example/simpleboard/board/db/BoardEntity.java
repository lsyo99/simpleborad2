package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;
    private String status;
    // 1:N관계를 설정한다.
    // 추가로 postEntity에서도 수정을 해야한다
    @OneToMany(mappedBy = "board")
    @Builder.Default
    @Where(clause = "status='registered'")
    @OrderBy("id desc")
    private List<PostEntity> postList = List.of();


}
