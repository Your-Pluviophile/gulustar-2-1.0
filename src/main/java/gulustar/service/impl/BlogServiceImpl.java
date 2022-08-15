package gulustar.service.impl;

import gulustar.mapper.BlogMapper;
import gulustar.pojo.Blog;
import gulustar.pojo.BlogPageBean;
import gulustar.pojo.Comment;
import gulustar.pojo.Conditions;
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

    /**
     * 添加一条评论 对象里包含博客，用户ID 和 评论内容
     * @param comment
     * @return
     */
    @Override
    public boolean addComment(Comment comment) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        //添加评论到评论表
        boolean addComment = blogMapper.addComment(comment);
        //获取自增值 = 新增博客的ID
        Integer commentId = blogMapper.selectLastInsertId();
        //添加2个ID到blog_comment表，建立对应关系
        Integer blogId = comment.getBlogId();
        boolean addCommentRelation = blogMapper.addCommentRelation(blogId, commentId);

        sqlSession.commit();
        sqlSession.close();
        return addComment && addCommentRelation;
    }

    /**
     * 获取指定ID博客 包含博客内容和评论
     * @param id
     * @return
     */
    @Override
    public Blog getOneBlog(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        //查询博客内容和评论集合,把评论集合封装到博客对象里
        Blog blog = blogMapper.selectOneBlog(id);
        List<Integer> commentIds = blogMapper.selectCommentIdList(id);
        List<Comment> comments = blogMapper.selectCommentsByIds(commentIds);
        blog.setComment(comments);

        sqlSession.close();
        return blog;
    }

    /**
     * 根据条件查询博客
     * @return
     */
    @Override
    public BlogPageBean getBlogsByPageAndCondition(Conditions conditions) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        //计算limit的开始位置
        Integer currentPage = conditions.getCurrentPage();
        Integer size = conditions.getSize();
        int start = (currentPage - 1) * size;

        //获取符合条件博客集合
        List<Blog> blogs = mapper.selectByPageAndCondition(conditions, start, size);
        //计算总页数
        Integer count = mapper.selectCountByCondition(conditions);
        Integer totalPage = count / size;
        totalPage = (totalPage * size < count)? totalPage + 1: totalPage;

        //封装为pageBean对象
        BlogPageBean pageBean = new BlogPageBean();
        pageBean.setBlogs(blogs);
        pageBean.setTotal(totalPage);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public List<Blog> getAllBlogs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> blogs = mapper.selectAll();
        return blogs;
    }

    /**
     * 根据分类ID查询：返回博客对象数组
     * @param category
     * @return
     */
    @Override
    public List<Blog> queryByCategory(String category) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> blogs = mapper.selectByCategory(category);
        sqlSession.close();
        return blogs;
    }

    /**
     * 根据关键词查询
     * @param keyword
     * @return
     */
    @Override
    public List<Blog> queryByKeyword(String keyword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> blogs = mapper.selectByKeyword(keyword);
        sqlSession.close();
        return blogs;
    }

    /**
     * 根据用户id查询收藏的博客
     * @param
     * @return
     */
    @Override
    public BlogPageBean selectCollect(Integer userId, Conditions conditions) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        //设置查询条件
        Integer page = conditions.getCurrentPage();
        Integer size = conditions.getSize();
        int start = (page - 1) * size;
        conditions.setCurrentPage(start);

        //获取收藏的博客集合
        List<Blog> blogs = mapper.selectCollect(userId, conditions);
        //获取收藏博客总数 计算页数
        Integer count = mapper.selectCollectCount(userId, conditions);
        Integer totalPage = count / size;
        totalPage = (totalPage * size < count)? totalPage + 1: totalPage;

        //封装为pageBean对象
        BlogPageBean pageBean = new BlogPageBean();
        pageBean.setBlogs(blogs);
        pageBean.setTotal(totalPage);

        sqlSession.close();
        return pageBean;
    }
}
