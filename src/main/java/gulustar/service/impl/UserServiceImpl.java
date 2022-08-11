package gulustar.service.impl;

import gulustar.mapper.UserMapper;
import gulustar.pojo.User;
import gulustar.service.UserService;
import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User login(String username,String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByAccount(username,password);
        return user;
    }

    /**
     * 注册
     * @return
     */
    @Override
    public boolean registe(String account, String username, String password) {

        //获取userMapper对象 调用其方法
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //先确认同名用户是否存在
//      User user  userMapper.确认用户是否存在的方法(account)

        //如果不存在 就添加到数据库
        //封装为User对象穿给持久层
//        if user != null
//        User user = new User()
//        user.set username = username 等等
//        userMapper.添加用户的方法
        return false;
    }
}
