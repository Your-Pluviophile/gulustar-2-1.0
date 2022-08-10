package gulustar.pojo;

import java.sql.Date;

/**
 *  评论
 */
public class Comment {
    Integer id;
    String content;
    Integer userId;     //谁评论的
    Date createTime;    //评论时间
}
