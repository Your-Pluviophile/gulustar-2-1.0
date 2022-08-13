import gulustar.mapper.BlogMapper;
import gulustar.mapper.UserMapper;
import gulustar.pojo.Blog;
import gulustar.pojo.User;
import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

public class TestMapper {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Test
    public void testAddUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAccount("12443");
        user.setUsername("asdasd");
        user.setPassword("sdgagfasf");
        boolean b = userMapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(b);
    }

}
