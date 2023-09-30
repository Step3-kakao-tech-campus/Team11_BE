package com.kakao.golajuma.comment.domain.utils;

// 유저id로 판별하고 불러와야하는 데이터들이 있음
// public class SearchUserData {
//    //넣고자 하는 dto
//    private AbstractDto dto;
//
//    //유저 데이터 가져오기 로직
//    public List<AbstractDto> start(AbstractDto dto, List<CommentEntity> entityList) {
//        List<AbstractDto> DtoList = new ArrayList<>();
//
//        for (CommentEntity entity : entityList) {
//            Long id = entity.getUserId();
//            String username = "asdf"; // 데이터베이스에서 유저 닉네임 가져오기 위한 레포지토리가 들어갈 부분 - 미완성
//            //주인 판별 로직
//            if (userId == id)
//                isOwner = true;
//            else
//                isOwner = false;
//            readCommentDtoList.add(new ReadCommentDto(commentEntity, isOwner, username));
//        }
//        return DtoList;
//    }
// }
