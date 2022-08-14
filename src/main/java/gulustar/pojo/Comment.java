package gulustar.pojo;

import java.sql.Date;

/**
 *  评论
 */
public class Comment {
    Integer id;
    String content;
    String username;    //谁评论的 数据库里是用户ID 查询时使用多表查询直接封装用户名
    Date createTime;    //评论时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
