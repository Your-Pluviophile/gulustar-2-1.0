package gulustar.service.impl;

import gulustar.dao.mapper.BlogMapper;
import gulustar.dao.pojo.Blog;
import gulustar.service.BlogService;

import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author spp
 * @create 2022-08-12 16:13
 */
public class BlogServiceImpl implements BlogService{
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public Blog[] selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog[] blogs = mapper.selectAll();
        return blogs;
    }

    @Override
    public List<Blog> QueryByCategory(String categoryIdStr) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        //将ID转换为数字
        int categoryId = Integer.parseInt(categoryIdStr);
        return mapper.selectByCategory(categoryId);
    }

    /**
     * 根据用户id查询收藏的博客
     * @param id
     * @return
     */
    @Override
    public List<Blog> selectCollect(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogs = mapper.selectCollect(id);
        sqlSession.close();
        return blogs;
    }
}
