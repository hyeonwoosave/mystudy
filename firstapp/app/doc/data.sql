-- games 데이터
insert into games(game_no,title,rating,price,produce,genre,info,release_date)
  values(1,'게임1','8','34900','제작사1','장르1','소개1','2018-08-09');
insert into games(game_no,title,rating,price,produce,genre,info,release_date)
  values(2,'게임2','6','0','제작사2','장르2','소개2','2021-12-03');
insert into games(game_no,title,rating,price,produce,genre,info,release_date)
  values(3,'게임3','10','27000','제작사3','장르3','소개3','2011-05-17');

-- reviews 데이터
insert into reviews(review_no,title,rating,content,writer,category)
  values(1,'제목1','9','내용1',101,1);
insert into reviews(review_no,title,rating,content,writer,category)
  values(2,'제목2','1','내용2',102,1);
insert into reviews(review_no,title,rating,content,writer,category)
  values(3,'제목3','6','내용3',103,2);

-- users 데이터
insert into users(user_no,name,tel,email,password,created_date)
  values(101,'user1','010-1111-1111','user1@test.com',sha2('1111',256),'2024-1-1');
insert into users(user_no,name,tel,email,password,created_date)
  values(102,'user2','010-2222-2222','user2@test.com',sha2('1111',256),'2024-2-2');
insert into users(user_no,name,tel,email,password,created_date)
  values(103,'user3','010-3333-3333','user3@test.com',sha2('1111',256),'2024-3-3');

-- freeboards 데이터
insert into freeboards(freeboard_no,title,content,writer)
  values(1,'제목1','내용1',101);
insert into freeboards(freeboard_no,title,content,writer)
  values(2,'제목2','내용2',102);
insert into freeboards(freeboard_no,title,content,writer)
  values(3,'제목3','내용3',102);

-- qnas 데이터
insert into qnas(qna_no,title,content,writer)
  values(1,'제목1','내용1',101);
insert into qnas(qna_no,title,content,writer)
  values(2,'제목2','내용2',102);
insert into qnas(qna_no,title,content,writer)
  values(3,'제목3','내용3',102);


