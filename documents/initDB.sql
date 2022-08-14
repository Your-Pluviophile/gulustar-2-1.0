CREATE DATABASE gulustar_DB;
USE gulustar_DB;


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
)

#历史浏览记录
CREATE TABLE user_history(
	user_id INT,
	blog_id INT
)

#------------------以上为用户相关-------------------------------------



#-----------------------博客表---------------------------------------

# 博客表
CREATE TABLE blog(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,				#作者
	category INT,
	title VARCHAR(100),
	`description` VARCHAR(100),		#简介
	content VARCHAR(100),
	likes INT,
	collected INT,
	STATUS INT,
	release_date TIMESTAMP,
	modify_date TIMESTAMP
)

# 分类表
CREATE TABLE category(
	id INT PRIMARY KEY,
	`name` VARCHAR(20)
)

# 博客的评论
CREATE TABLE blog_comment(
	blog_id INT,
	comment_id INT
)

# 评论表
CREATE TABLE `comment`(
	id INT PRIMARY KEY AUTO_INCREMENT,
	content VARCHAR(1000),
	user_id INT,
	create_time TIMESTAMP
)

#-----------------------------以上博客---------------------------------------------

