package gulustar.service.impl;

import gulustar.mapper.BlogMapper;
import gulustar.pojo.Blog;
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
    public List<Blog> selectCondition(Blog blog) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        //构造模糊查询条件
        if (blog.getDescription() != null && blog.getDescription().length() > 0) {
            blog.setDescription("%" + blog.getDescription() + "%");
        }
        if (blog.getTitle()!= null && blog.getTitle().length() > 0) {
            blog.setTitle("%" + blog.getTitle()+ "%");
        }
        return mapper.selectCondition(blog);
    }
}
