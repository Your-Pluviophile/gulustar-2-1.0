package gulustar.pojo;

import java.sql.Date;
import java.util.List;

/**
 *  博客
 */
public class Blog {
    Integer id;
    Integer userId;         //作者
    Integer category;       //分类
    String title;           //标题
    String description;     //简介
    String content;         //内容 存html的地址
    Integer likes;          //这篇博客的点赞数
    Integer colleted;       //收藏的人数
    List<String> comment;   //博客的评论
    Integer status;         //状态(是否已被删除)
    Date releaseDate;       //发布时间
    Date modifyDate;        //修改时间
}
