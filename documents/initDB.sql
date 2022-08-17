CREATE DATABASE gulustar_DB;
USE gulustar_DB;

drop table if exists user;
drop table if exists user_collection;
drop table if exists user_follow;
drop table if exists user_history;
drop table if exists blog;
drop table if exists category;
drop table if exists blog_comment;
drop table if exists comment;

#------------------以下为用户相关-------------------------------------

# 用户表
CREATE TABLE USER(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(10),
	`account` VARCHAR(12),
	`password` VARCHAR(20),
	is_admin INT,
	`status` INT
);

# 用户收藏
CREATE TABLE user_collection(
	user_id INT,
	blog_id INT
);

#用户关注
CREATE TABLE user_follow(
	user_id INT,
	follow_id INT #关注的人的ID
);

#历史浏览记录
CREATE TABLE user_history(
	user_id INT,
	blog_id INT,
    browse_time TIMESTAMP
);

#------------------以上为用户相关-------------------------------------



#-----------------------博客表---------------------------------------

# 博客表
CREATE TABLE blog(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,				#作者
	category varchar(100),
	title VARCHAR(100),
	`description` VARCHAR(100),	#简介
	content VARCHAR(10000),     #内容
	likes INT,                  #点赞数
	collected INT,              #收藏数
	STATUS INT,
	release_date TIMESTAMP,
	modify_date TIMESTAMP
);

# 博客的评论
CREATE TABLE blog_comment(
	blog_id INT,
	comment_id INT
);

# 评论表
CREATE TABLE `comment`(
	id INT PRIMARY KEY AUTO_INCREMENT,
	content VARCHAR(1000),
	user_id INT,
	create_time TIMESTAMP
);

#-----------------------------以上博客---------------------------------------------

#-----------------------------测试数据-------------------------------------------

INSERT INTO blog VALUES(NULL, 1, '测试分类1', '测试标题1', '测试简介1', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类2', '测试标题2', '测试简介2', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类3', '测试标题3', '测试简介3', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类4', '测试标题4', '测试简介4', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类5', '测试标题5', '测试简介5', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类6', '测试标题6', '测试简介6', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类7', '测试标题7', '测试简介7', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类8', '测试标题8', '测试简介8', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类9', '测试标题9', '测试简介9', '测试内容', 100, 200, 1, NOW(), NULL);
INSERT INTO blog VALUES(NULL, 1, '测试分类10', '测试标题10', '测试简介10', '测试内容', 100, 200, 1, NOW(), NULL);

#---------------------------------以上测试数据-------------------------------------------------------

