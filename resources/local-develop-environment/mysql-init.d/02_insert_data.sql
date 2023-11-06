use golajuma;
INSERT INTO user (user_id, user_nickname, user_email, user_password, created_date, updated_date, deleted)
VALUES
    (1, "진우석","jws@gmail.com","asdf1234", NOW(), NOW(),false),
    (2, "문지혜","mjh@gmail.com","asdf1234", NOW(), NOW(),false),
    (3, "박현수","phs@gmail.com","asdf1234", NOW(), NOW(),false),
    (4, "김수민","ksm@gmail.com","asdf1234", NOW(), NOW(),false),
    (5, "강성준","kky@gmail.com","asdf1234", NOW(), NOW(),false),
    (6, "김가연","ksj@gmail.com","asdf1234", NOW(), NOW(),false),
    (7, "유리", "yuri@gmail.com", "password1", NOW(), NOW(), false),
    (8, "태호", "taeho@gmail.com", "password2", NOW(), NOW(), false),
    (9, "지은", "jieun@gmail.com", "password3", NOW(), NOW(), false),
    (10, "민재", "minjae@gmail.com", "password4", NOW(), NOW(), false),
    (11, "예린", "yerin@gmail.com", "password5", NOW(), NOW(), false),
    (12, "성민", "seongmin@gmail.com", "password6", NOW(), NOW(), false),
    (13, "지훈", "jihun@gmail.com", "password7", NOW(), NOW(), false),
    (14, "민지", "minji@gmail.com", "password8", NOW(), NOW(), false),
    (15, "준호", "junho@gmail.com", "password9", NOW(), NOW(), false),
    (16, "유진", "yujin@gmail.com", "password10", NOW(), NOW(), false),
    (17, "동현", "donghyun@gmail.com", "password11", NOW(), NOW(), false),
    (18, "지우", "jiwoo@gmail.com", "password12", NOW(), NOW(), false),
    (19, "은지", "eunji@gmail.com", "password13", NOW(), NOW(), false),
    (20, "재원", "jaewon@gmail.com", "password14", NOW(), NOW(), false),
    (21, "서연", "seoyeon@gmail.com", "password15", NOW(), NOW(), false),
    (22, "민우", "minwoo@gmail.com", "password16", NOW(), NOW(), false),
    (23, "가온", "gaon@gmail.com", "password17", NOW(), NOW(), false),
    (24, "윤호", "yunho@gmail.com", "password18", NOW(), NOW(), false),
    (25, "서윤", "seoyun@gmail.com", "password19", NOW(), NOW(), false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (1, 1,"TOTAL", "축제 때 어떤 옷 입고갈까?", "낼모레 축제인데 츄리닝은 오바지?",
        NOW() + INTERVAL 3 DAY,"기본",NOW(), NOW(),false, 20);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (1, "캐쥬얼한 조끼니트",5,1,null,NOW(), NOW(),false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (2, "힙합!",10,1,null,NOW(), NOW(),false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (3, "츄리닝",5,1,null,NOW(), NOW(),false);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (1,1,2,"츄리닝을 고민하냐 이양반아",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (2,1,2,"아니 누구랑 가는데?",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (3,1,2,"부럽다.. 인싸구나",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (4,1,3,"축제는 무조건 힙합이긴함",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (5,1,3,"나도 츠리닝 입고 갔는데 다들 꾸미고 와서 부끄러웠음..",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (6,1,3,"추리닝이 선택지에 있는거 자체가 에바임ㅋㅋ",NOW(), NOW(),false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 11, 1);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 12, 1);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 13, 1);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 14, 1);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 15, 1);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW(), false, NOW(), 1, 2),
                                                                                                     (NOW(), false, NOW(), 2, 2),
                                                                                                     (NOW(), false, NOW(), 3, 2),
                                                                                                     (NOW(), false, NOW(), 4, 2),
                                                                                                     (NOW(), false, NOW(), 5, 2),
                                                                                                     (NOW(), false, NOW(), 6, 2),
                                                                                                     (NOW(), false, NOW(), 7, 2),
                                                                                                     (NOW(), false, NOW(), 8, 2),
                                                                                                     (NOW(), false, NOW(), 9, 2),
                                                                                                     (NOW(), false, NOW(), 10, 2);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 16, 3);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 17, 3);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 18, 3);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 19, 3);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW(), false, NOW(), 20, 3);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (2, 2,"MOVIE", "넷플릭스 뭐 볼지 추천 좀 ㅠㅠ", "재밌는게 너무 많앙",
        NOW() + INTERVAL 3 DAY,"기본",NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 1 MINUTE,false,8);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (4,"무서운이야기",5,2,null,NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 1 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (5,"오징어게임3",3,2,null,NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 1 MINUTE,false);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (7,2,4,"아니 근데 오징어게임3 안나옴 아직",NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 1 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (8,2,4,"띵작 좀 아네..무서운이야기 좋아하면 브리저튼도 봐바",NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 1 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (9,2,4,"장르가 딴판이네..",NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 1 MINUTE,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false,NOW() + INTERVAL 1 MINUTE, 1, 4),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 2, 4),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 3, 4),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 4, 4),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 5, 4),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 6, 5),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 7, 5),
                                                                                                     (NOW() + INTERVAL 1 MINUTE, false, NOW() + INTERVAL 1 MINUTE, 8, 5);


INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (3, 2,"TOTAL", "학교가기싫다", "ㅇㅈ?",
        NOW() + INTERVAL 3 DAY,"기본",NOW() + INTERVAL 2 MINUTE, NOW() + INTERVAL 2 MINUTE,false,10);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (6,"갈까?",0,3,null,NOW() + INTERVAL 2 MINUTE, NOW() + INTERVAL 2 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (7,"말까?",10,3,null,NOW() + INTERVAL 2 MINUTE, NOW() + INTERVAL 2 MINUTE,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 1, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 2, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 3, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 4, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 5, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 6, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 7, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 8, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 9, 7),
                                                                                                     (NOW() + INTERVAL 2 MINUTE, false, NOW() + INTERVAL 2 MINUTE, 10, 7);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (10,3,1,"나도...",NOW() + INTERVAL 2 MINUTE, NOW() + INTERVAL 2 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (11,3,1,"ㅋㅋ찔렸다ㅋㅋ",NOW() + INTERVAL 2 MINUTE, NOW() + INTERVAL 2 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (12,3,1,"좀 쳐가라 제발 나중에 후회함",NOW() + INTERVAL 2 MINUTE, NOW() + INTERVAL 2 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (13,3,1,"ㅋㅋ난 직장인이라 학교안감",NOW() + INTERVAL 2 MINUTE, NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (14,3,1,"윗댓아 출근하자..",NOW(), NOW(),false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted,vote_total_count)
VALUES (4, 3,"FOOD", "여자친구랑 데이트 뭐 먹을까요?", "넷 중에 하나로 갈 예정임",
        NOW() + INTERVAL 3 DAY,"기본",NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false, 12);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (8,"짜이나궁",1,4,null,NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (9,"라쿵푸마라탕",2,4,null,NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (10,"로니로티",4,4,null,NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (11,"상무초밥",5,4,null,NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 11, 8);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 12, 9);
INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 13, 9);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 1, 10),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 2, 10),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 3, 10),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 4, 10),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 5, 11),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 6, 11),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 7, 11),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 8, 11),
                                                                                                     (NOW() + INTERVAL 3 MINUTE, false, NOW() + INTERVAL 3 MINUTE, 9, 11);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (15,4,1,"아니 ",NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (16,4,1,"이걸 고민이라고 올리냐? 제발",NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (17,4,1,"난 마라탕도 괜찮은거 같아",NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (18,4,1,"선택지들이 너무 똥이라 그나마 냄새덜한 똥을 골라야하네;;;",NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (19,4,1,"그..여친이 전대생이면 로니로티는 좀 질려할듯",NOW() + INTERVAL 3 MINUTE, NOW() + INTERVAL 3 MINUTE,false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (5, 4,"TOTAL", "군대..가야하나?", "이제 대학교 2학년 올라가는데 군대를 가야할지 말아야할지 고민이에요.. 사실 얼마 안 된 여친이 있는데 헤어지기 너무 싫어요",
        "2023-9-27","기본",NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false, 6);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (12,"걍 가라",5,5,null,NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (13,"가지마라",1,5,null,NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 5 MINUTE, 4, 12),
                                                                                                     (NOW() + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 5 MINUTE, 5, 12),
                                                                                                     (NOW() + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 5 MINUTE, 6, 12),
                                                                                                     (NOW() + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 5 MINUTE, 7, 12),
                                                                                                     (NOW() + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 5 MINUTE, 8, 12),
                                                                                                     (NOW() + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 5 MINUTE, 9, 13);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (20,5,2,"여친을 두고가다니..너무하네요..헤어지고 가세요;;",NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (21,5,2,"ㅋㅋ근데 늦게 가면 갔다올 때 친구 없는게 개 에바긴함",NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (22,5,2,"일말상초각ㅋㅋ",NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (23,5,2,"사귄지 얼마 안된거면 일말상초도 아님 요즘엔 훈말이초임",NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 5 MINUTE,false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (6, 3,"TOTAL", "대학원엘 갈지 취업을 해서 경험을 먼저 쌓을지가 고민입니다", "취업하기가 무섭다 애들아..",
        NOW() + INTERVAL 3 DAY,"기본",NOW() + INTERVAL 1 HOUR, NOW() + INTERVAL 1 HOUR,false, 10);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (14,"대학원",0,6,null,NOW() + INTERVAL 1 HOUR, NOW() + INTERVAL 1 HOUR,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (15,"취업",10,6,null,NOW() + INTERVAL 1 HOUR, NOW() + INTERVAL 1 HOUR,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 1, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 2, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 3, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 4, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 5, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 6, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 7, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 8, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 10, 15),
                                                                                                     (NOW() + INTERVAL 1 HOUR, false, NOW() + INTERVAL 1 HOUR, 9, 15);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (24,6,3,"가.지.마",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (25,6,3,"그.냥.가.지.마",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (26,6,3,"ㅋㅋ대학원생들 왤케 많음 ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (27,6,3,"진지하게 이걸 고민중이면 취업을 하는게 맞음. 나도 대학원생인데 대학원은 저 마인드로 오면 못 버팀",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (28,6,3,"이 투표는 전국대학원생협회에서 점령했습니다",NOW(), NOW(),false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (7, 1,"WHAT", "과제를 지금 할까 말까", "오늘 12시까지인데 하기 너무 싫어 ㅠㅠ",
        "2023-09-26","기본",NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false,6);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (16,"지금 하자",3,7,null,NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (17,"좀만 놀다 하자",3,7,null,NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (18,"한번은 제껴도 됨",4,7,null,NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 1, 16),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 2, 16),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 3, 16),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 4, 17),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 5, 17),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 6, 17),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 7, 18),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 8, 18),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 10, 18),
                                                                                                     (NOW() + INTERVAL 2 HOUR, false, NOW() + INTERVAL 2 HOUR, 9, 18);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (29,7,4,"와 진짜 지금 나임 ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (30,7,4,"좋을때다..",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (31,7,4,"아니 얼마나 했는데?",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (32,7,4,"방금알았어요 ㅜㅜ",NOW(), NOW(),false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (8, 1,"TOTAL", "제가 팔랑귀인지 고민이에요", "그 종종 다른 사람들이 이 수업 좋다좋다 하길래 다 하는데..지금 그래서 이번학기에 경제, 컴공, 심리 강의 다
듣게 됐거든..이거 팔랑귀야?",
        NOW() + INTERVAL 3 DAY,"기본",NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false, 0);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (19,"맞다",0,8,null,NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (20,"그럴 수 있다",0,8,null,NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);


INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (33,8,2,"ㅋㅋㅋ팔랑귀로 헤르미온느 됐네 ㅋㅋ",NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (34,8,2,"다 잘하면 됨 화이팅",NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (35,8,2,"그래서 님드라..답변이나 해줘요..",NOW() + INTERVAL 2 HOUR, NOW() + INTERVAL 2 HOUR,false);


INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (9, 2,"TOTAL", "컴활 딸까 말까?", "컴활 진짜 중요해? 따는거 추천함?",
        "2023-10-20","기본",NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false, 8);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (21,"딴다",4,9,null,NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (22,"그걸 왜 함",4,9,null,NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 1, 21),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 2, 21),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 3, 21),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 4, 21),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 5, 22),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 6, 22),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 7, 22),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, 8, 22);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (36,9,3,"남들 다 따길래 땀",NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (37,9,3,"걍 개쉬우니까 해봐",NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (38,9,3,"공기업갈거 아니면 비추",NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (39,9,3,"그걸 왜 함 까지는 아닌데 굳이싶기는 함",NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 5 MINUTE,false);

INSERT INTO vote (vote_id, user_id, vote_category, vote_title, vote_content,
                  vote_end_date, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (10, 10,"TOTAL", "어떤 분이 나한테 잘 맞을지 모르겠어", "성격은 진짜 잘 맞는데 내 스탈은 아닌 동생이 있고 이번에 소개팅나갔는데 얼굴은 예쁘신데 난 조용한걸 별로 안좋아해서..",
        NOW() + INTERVAL 3 DAY,"기본",NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false,11);

INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (23,"전자",3,10,null,NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false);
INSERT INTO vote_option (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (24,"후자",8,10,null,NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 11, 23),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 22, 23),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 23, 23);

INSERT INTO decision (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 1, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 2, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 3, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 4, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 5, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 6, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 7, 24),
                                                                                                     (NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, false, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, 8, 24);

INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (40,10,2,"조용한 여자면 너가 말 많으면 되지",NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (41,10,3,"난 무조건 얼굴",NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (42,10,4,"내 성격이 까다로운 건 아닌데 근데 나는 안맞는 사람은 별로 안끌림",NOW(), NOW(),false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (43,10,1,"나는 중자",NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false);
INSERT INTO comment (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (44,10,2,"윗댓아 선택지 만들지 마셈..이미 투표도 한 사람이..",NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE, NOW() + INTERVAL 2 HOUR + INTERVAL 7 MINUTE,false);
