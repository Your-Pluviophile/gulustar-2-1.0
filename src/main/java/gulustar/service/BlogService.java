package gulustar.service;

import gulustar.pojo.Blog;

import java.util.List;

/**
 * @author spp
 * @create 2022-08-12 16:12
 */
public interface BlogService {

    /**
     * 获取所有博客
     * @return
     */
    List<Blog> getAllBlogs();

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
     * @param id
     */
    List<Blog> selectCollect(Integer id);
}
