-- DDL(Data Definition Language)

drop table if exists games restrict;
drop table if exists reviews restrict;
drop table if exists users restrict;
drop table if exists freeboards restrict;
drop table if exists qnas restrict;

create table games(
  game_no int not null,
  title varchar(255) not null,
  rating int not null,
  price int not null,
  produce varchar(255) not null,
  genre varchar(255) not null,
  info text not null,
  release_date date not null
);

alter table games
  add constraint primary key (game_no),
  modify column game_no int not null auto_increment;

create table reviews(
  review_no int not null,
  title varchar(255) not null,
  rating int not null,
  content text not null,
  writer int not null,
  category int not null,
  created_date datetime null default now()
);

alter table reviews
  add constraint primary key (review_no),
  modify column review_no int not null auto_increment;

create table freeboards(
  freeboard_no int not null,
  title varchar(255) not null,
  content text not null,
  writer int not null,
  created_date datetime null default now()
);

alter table freeboards
  add constraint primary key (freeboard_no),
  modify column freeboard_no int not null auto_increment;

create table qnas(
  qna_no int not null,
  title varchar(255) not null,
  content text not null,
  writer int not null,
  created_date datetime null default now()
);

alter table qnas
  add constraint primary key (qna_no),
  modify column qna_no int not null auto_increment;

create table users(
  user_no int not null,
  name varchar(255) not null,
  tel varchar(255) not null,
  email varchar(255) not null,
  password varchar(100) not null,
  created_date datetime null default now()
);

alter table users
  add constraint primary key (user_no),
  modify column user_no int not null auto_increment;
