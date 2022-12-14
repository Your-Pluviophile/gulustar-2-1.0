package gulustar.service;

import gulustar.pojo.Blog;
import gulustar.pojo.BlogPageBean;
import gulustar.pojo.Comment;
import gulustar.pojo.Conditions;

import javax.swing.text.StyledEditorKit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author spp
 * @create 2022-08-12 16:12
 */
public interface BlogService {

    /**
     * 添加博客
     * @return
     */
    boolean addBlog(Blog blog);

    /**
     * 用户发表一条评论
     * @return
     */
    boolean addComment(Comment comment);

    /**
     * 获取指定ID博客
     * @return
     */
    Blog getOneBlog(Integer id) throws IOException;

    /**
     * 根据条件查询博客
     * @return
     */
    BlogPageBean getBlogsByPageAndCondition(Conditions conditions);

    /**
     * 根据分类查询博客
     * @param
     * @return
     */
    List<Blog> queryByCategory(String categoryIdStr);

    /**
     * 根据关键词查询
     * @param keyword
     * @return
     */
    List<Blog> queryByKeyword(String keyword);

    /**
     * 根据用户id查询收藏的博客
     * @param
     */
    BlogPageBean selectCollect(Integer userId, Conditions conditions);

    /**
     * 获取全部分类
     * @return
     */
    List<String> getAllCategories();

    /**
     * 增加点赞数
     * @return
     */
    boolean addLikes(Integer blogId);
}
