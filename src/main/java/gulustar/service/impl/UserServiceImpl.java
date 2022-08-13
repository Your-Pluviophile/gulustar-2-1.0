package gulustar.service.impl;

import gulustar.dao.mapper.UserMapper;
import gulustar.dao.pojo.User;
import gulustar.service.UserService;
import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    @Override
    public User login() {
        return null;
    }

    @Override
    public boolean registe() {
        return false;
    }
}
