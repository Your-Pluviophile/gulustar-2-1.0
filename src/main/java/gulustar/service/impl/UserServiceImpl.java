package gulustar.service.impl;

import gulustar.mapper.BlogMapper;
import gulustar.mapper.UserMapper;
import gulustar.pojo.*;
import gulustar.service.UserService;
import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 获取指定ID用户信息
     * @return
     */
    public User getUserById(Integer id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(id);
        sqlSession.close();
        return user;
    }

    /**
     * 登录方法：根据账号密码查询用户，并封装成对象返回
     * @param account
     * @param password
     * @return
     */
    @Override
    public User login(String account, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByAccAndPwd(account, password);
        sqlSession.close();
        return user;
    }

    /**
     * 注册方法：把用户信息封装为对象调用mapper方法添加到数据库
     * @return
     */
    @Override
    public boolean registe(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //获取用户信息
        String account = user.getAccount();
        String username = user.getUsername();

        //确认同名用户是否存在
        User isExsitsAcc = userMapper.selectByAccount(account);
        User isExsitsNikename = userMapper.selectByUsername(username);
        if (isExsitsAcc == null){
            if (isExsitsNikename == null){
                //如果不存在同名同账号用户、将user对象存到数据库
                user.init();
                boolean insertRes = userMapper.addUser(user);
                sqlSession.commit();
                sqlSession.close();

                return insertRes;
            }
            else {
                //如果昵称存在，则注册失败
                sqlSession.close();
                return false;
            }
        }else{
            //如果账户存在，则注册失败
            sqlSession.close();
            return false;
        }
    }

    /**
     * 记录用户浏览历史
     * @param history
     */
    @Override
    public void addUserHistory(History history) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        BlogMapper blogmapper = sqlSession.getMapper(BlogMapper.class);

        //查询是否存在相同浏览记录
        History sameHistory = blogmapper.selectSameHistory(history);

        //如果有同样的历史记录则更新时间
        if (sameHistory != null){
            userMapper.updateUserHistory(history);
            sqlSession.commit();
            sqlSession.close();
            return ;
        }else {
            userMapper.addUserHistory(history);
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 根据用户id查询历史记录
     * @param
     * @return
     */
    @Override
    public BlogPageBean selectHistory(Integer userId, Conditions conditions) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置数据库limit的开始位置
        Integer size = conditions.getSize();
        Integer currentPage = conditions.getCurrentPage();
        currentPage = (currentPage - 1) * size;
        conditions.setCurrentPage(currentPage);

        //查询数据库
        List<Blog> blogs = mapper.selectAllHistoryByAccount(userId, conditions);
        //计算页数
        Integer count = mapper.selectHistoryCount(userId, conditions);
        Integer totalPage = count / size;
        totalPage = (totalPage * size < count)? totalPage + 1: totalPage;

        //封装为pageBean对象
        BlogPageBean pageBean = new BlogPageBean();
        pageBean.setBlogs(blogs);
        pageBean.setTotal(totalPage);

        sqlSession.close();
        return pageBean;
    }

    /**
     * 取消收藏
     * @param userId
     * @param blogId
     * @return
     */
    boolean deleteCollection(Integer userId,Integer blogId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        boolean deleteCollection = userMapper.deleteCollection(userId, blogId);
        return deleteCollection;
    }

    /**
     * 收藏博客
     * @param userId
     * @param blogId
     * @return
     */
    public boolean collect(Integer userId, Integer blogId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        //添加数据
        boolean collectOK = userMapper.collectBlog(userId, blogId);
        boolean updateOK = blogMapper.updateBlogCollected(blogId);
        sqlSession.commit();

        sqlSession.close();
        return collectOK && updateOK;
    }

    /**
     * 获取用户收藏的博客ID集合
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getCollectBlogIds(Integer userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<Integer> ids = userMapper.selectCollectBlogIds(userId);

        sqlSession.close();
        return ids;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUserInfo(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        boolean update = userMapper.updateById(user);
        sqlSession.commit();
        sqlSession.close();

        return update;
    }

}

