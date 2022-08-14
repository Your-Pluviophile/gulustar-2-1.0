package gulustar.pojo;

import java.util.List;

/**
 *  封装一页的博客集合和总页数
 */
public class BlogPageBean {
    private Integer total;
    private List<Blog> blogs;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
