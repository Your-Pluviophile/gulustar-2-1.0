package gulustar.dao.pojo;

import java.sql.Date;

/**
 *  评论
 */
public class Comment {
    Integer id;
    String content;
    String imageUrl;    //评论者头像图片
    String username;    //谁评论的 数据库里是用户ID 查询时使用多表查询直接封装用户名
    Integer userId;     //前端新增评论的时候封装JSON用 用户ID
    Integer blogId;     //前端新增评论的时候封装JSON用 博客ID
    Date createTime;    //评论时间

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
