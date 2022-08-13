import gulustar.mapper.UserMapper;
import gulustar.pojo.User;
import gulustar.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestUserMapper {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    User user = new User();
    @Test
    public void testAddUser(){

        user.setAccount("12443");
        user.setUsername("asdasd");
        user.setPassword("sdgagfasf");
        boolean b = userMapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(b);
    }

    @Test
    public void testSelectAllFollowsByAccount(){
        user.setAccount("12443");
        user.setUsername("asdasd");
        user.setPassword("sdgagfasf");
        LinkedList<Integer> users = new LinkedList<>();
        users.add(user.getId());
        user.setFollow(users);
        List<User> users1 = userMapper.selectAllFollowsByAccount(user.getId());
        sqlSession.commit();
        sqlSession.close();
        System.out.println(users1);
    }

}
