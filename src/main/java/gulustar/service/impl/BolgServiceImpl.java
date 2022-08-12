package gulustar.service.impl;

import gulustar.mapper.BlogMapper;
import gulustar.pojo.Blog;
import gulustar.service.BolgService;

import javax.jnlp.BasicService;
import java.net.URL;

import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/**
 * @author spp
 * @create 2022-08-12 16:13
 */
public class BolgServiceImpl implements BolgService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public Blog[] selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog[] blogs = mapper.selectAll();
        return blogs;
    }
}
