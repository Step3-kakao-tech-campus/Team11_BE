 package com.kakao.golajuma.comment.infra.entity;
 import com.kakao.golajuma.common.BaseEntity;

 import lombok.*;
 import lombok.experimental.SuperBuilder;

 import javax.persistence.*;

 import static com.kakao.golajuma.comment.infra.entity.CommentEntity.ENTITY_PREFIX;

 @Getter @Setter
 @SuperBuilder
 @NoArgsConstructor(access = AccessLevel.PROTECTED)
 @AllArgsConstructor(access = AccessLevel.PRIVATE)
 @Entity
 @Table(name= ENTITY_PREFIX + "_tb")
 public class CommentEntity extends BaseEntity {

    public static final String ENTITY_PREFIX = "comment";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ENTITY_PREFIX + "_id", unique = true, nullable = false)
    private Long id;

    @Column(name = ENTITY_PREFIX + "_vote_id", length = 15, nullable = false)
    private Long voteId;

    @Column(name = ENTITY_PREFIX + "_user_id", length = 15, nullable = false)
    private Long userId;

    @Column(name = ENTITY_PREFIX + "_content", length = 255, nullable = false)
    private String content;

     @Builder
     public CommentEntity(long voteId, long userId, String content) {
         this.voteId = voteId;
         this.userId = userId;
         this.content = content;
     }
 }
