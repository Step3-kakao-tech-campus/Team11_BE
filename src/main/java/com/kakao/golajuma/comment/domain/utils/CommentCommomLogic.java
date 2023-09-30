// package com.kakao.golajuma.comment.domain.utils;
//
// import com.kakao.golajuma.comment.infra.entity.CommentEntity;
// import com.kakao.golajuma.comment.web.dto.response.ReadCommentDto;
//
// import java.util.ArrayList;
// import java.util.List;
//
// public class CommentCommomLogic {
//    boolean isOwner;
//    //공통 dto채워넣기
//    public List<ReadCommentDto> entityToDtoListWithUserData(){
//        List<ReadCommentDto> readCommentDtoList = new ArrayList<>();
//
//        for (CommentEntity commentEntity : commentEntityList) {
//        Long id = commentEntity.getUserId();
//        String username = "asdf"; // 데이터베이스에서 유저 닉네임 가져오기 위한 레포지토리가 들어갈 부분 - 미완성
//        //주인 판별 로직
//        if (userId == id)
//            isOwner = true;
//        else
//            isOwner = false;
//        readCommentDtoList.add(new ReadCommentDto(commentEntity, isOwner, username));
//        }
//        return readCommentDtoList;
//    }
// }

// 유저 데이터를 끌고와 자신이 맞는지 확인하거나, 투표인증은 우리 서비스의 거의 대부분에 쓰임 따로 util로 마련하면 어떨까 하는 생각
