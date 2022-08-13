package gulustar.service;

import gulustar.dao.pojo.Blog;

import java.util.List;

/**
 * @author spp
 * @create 2022-08-12 16:12
 */
public interface BlogService {
    Blog[] selectAll();

    /**
     * 根据分类查询博客
     * @param
     * @return
     */
    List<Blog> QueryByCategory(String categoryIdStr);

    /**
     * 根据用户id查询收藏的博客
     * @param id
     */
    List<Blog> selectCollect(Integer id);
}
