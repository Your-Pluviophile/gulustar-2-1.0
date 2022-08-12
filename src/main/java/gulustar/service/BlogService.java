package gulustar.service;

import gulustar.pojo.Blog;

import java.util.List;

/**
 * @author spp
 * @create 2022-08-12 16:12
 */
public interface BlogService {
    Blog[] selectAll();

    /**
     * 根据条件查询
     * @param blog
     * @return
     */
    List<Blog> selectCondition(Blog blog);
}
