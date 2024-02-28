-- DDL(Data Definition Language)

drop table if exists games restrict;
drop table if exists reviews restrict;
drop table if exists users restrict;
drop table if exists freeboards restrict;
drop table if exists freeboard_files restrict;
drop table if exists comments restrict;
drop table if exists qnas restrict;
drop table if exists answers restrict;

-- game
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

--review
create table reviews(
  review_no int not null,
  rating int not null,
  content text not null,
  writer int not null,
  category int not null,
  created_date datetime null default now()
);

alter table reviews
  add constraint primary key (review_no),
  modify column review_no int not null auto_increment;

--freeboard
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

--freeboard_file
create table freeboard_files(
  file_no int not null,
  file_path varchar(255) not null,
  freeboard_no int not null
);

alter table freeboard_files
  add constraint primary key (file_no),
  modify column file_no int not null auto_increment,
  add constraint freeboard_files_fk foreign key (freeboard_no) references freeboards(freeboard_no);

--comment
create table comments(
  comment_no int not null,
  content text not null,
  created_date datetime null default now()
);

alter table comments
add constraint primary key (comment_no),
modify column comment_no int not null auto_increment;

--qna
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

--answer
create table answers(
  answer_no int not null,
  content text not null,
  created_date datetime null default now()
);

alter table answers
add constraint primary key (answer_no),
modify column answer_no int not null auto_increment;

--user
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
  add constraint users_uk unique (email);

alter table freeboards
  add constraint freeboards_fk foreign key (writer) references users(user_no);

alter table comments
  add constraint comments_fk foreign key (writer) references users(user_no);

alter table qnas
  add constraint qnas_fk foreign key (writer) references users(user_no);

alter table reviews
  add constraint reviews_fk foreign key (writer) references users(user_no);
