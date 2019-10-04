create table question
(
	id int auto_increment,
	creator int,
	title varchar2(64),
	description text,
	tag varchar2(256),
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	gmt_create bigint,
	gmt_modified bigint,
	constraint question_pk
		primary key (id)
);

comment on table question is '问题描述表';