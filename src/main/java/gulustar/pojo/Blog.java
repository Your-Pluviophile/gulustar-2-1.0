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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getColleted() {
        return colleted;
    }

    public void setColleted(Integer colleted) {
        this.colleted = colleted;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
