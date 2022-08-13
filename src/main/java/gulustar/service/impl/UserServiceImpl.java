package gulustar.service.impl;

import gulustar.mapper.UserMapper;
import gulustar.pojo.User;
import gulustar.service.UserService;
import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    /**
     * 登录方法：根据账号密码查询用户，并封装成对象返回
     * @param account
     * @param password
     * @return
     */
    @Override
    public User login(String account, String password) {
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
     * 通过用户id 查询用户关注的人
     */
    @Override
    public List<User> selectAllFollowByAccount(User user) {
        //根据用户id，查询当前用户关注的人id
        List<Integer> follow_ids = userMapper.selectAllFollowsByAccount(user.getId());
        List<User> users = null;
        for (int id : follow_ids) {
            //根据用户id查询用户
            users.add(userMapper.selectById(id));
        }
        sqlSession.close();
        return users;
    }
}

