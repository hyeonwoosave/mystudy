
drop table games;

create table games(
  game_no int primary key auto_increment,
  title varchar(255) not null,
  produce varchar(255) not null,
  price varchar(100) not null,
  genre text not null,
  release_date date not null
);

insert into games(game_no,title,produce,price,genre,release_date)
  values(1,'몬헌','CAPCOM','34900','액션','2018-08-09');
insert into games(game_no,title,produce,price,genre,release_date)
  values(2,'롤','RiotGames','무료','AOS','2011-12-04');
insert into games(game_no,title,produce,price,genre,release_date)
  values(3,'레식','Ubisoft','22000','FPS','2015-12-01');
insert into games(game_no,title,produce,price,genre,release_date)
  values(4,'메이플','NEXON','무료','MMORPG','2003-04-29');
insert into games(game_no,title,produce,price,genre,release_date)
  values(5,'포켓몬','GameFreak','65000','RPG','2011-04-21');

select * from games;

drop table reviews;

create table reviews(
  review_no int primary key auto_increment,
  game varchar(255) not null,
  title varchar(255) not null,
  rating varchar(30) not null,
  content text null,
  writer varchar(30) not null,
  created_date datetime null default now()
);

insert into reviews(review_no,game,title,rating,content,writer)
  values(1,'롤','대충 롤얘기','2','리뷰1','ㅇㅇ1');
insert into reviews(review_no,game,title,rating,content,writer)
  values(2,'포켓몬','대충 포켓몬얘기','9','리뷰2','ㅇㅇ2');
insert into reviews(review_no,game,title,rating,content,writer)
  values(3,'레식','대충 레식얘기','4','리뷰3','ㅇㅇ3');
insert into reviews(review_no,game,title,rating,content,writer)
  values(4,'몬헌','대충 몬헌얘기','10','리뷰4','ㅇㅇ4');

select * from reviews;

drop table users;

create table users(
  user_no int primary key auto_increment,
  email varchar(255) not null,
  name varchar(255) not null,
  password varchar(100) not null,
  created_date datetime null default now()
);

insert into users(email,name,password,created_date)
  values('user1@test.com','유저1',sha2('1111',256),'2024-1-1');
insert into users(email,name,password,created_date)
  values('user2@test.com','유저2',sha2('1111',256),'2024-2-2');
insert into users(email,name,password,created_date)
  values('user3@test.com','유저3',sha2('1111',256),'2024-3-3');
insert into users(email,name,password,created_date)
  values('user4@test.com','유저4',sha2('1111',256),'2024-4-4');
insert into users(email,name,password,created_date)
  values('user5@test.com','유저5',sha2('1111',256),'2024-5-5');

select * from users;