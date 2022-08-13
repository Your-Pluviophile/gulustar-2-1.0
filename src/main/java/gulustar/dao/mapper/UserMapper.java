package gulustar.dao.mapper;

import gulustar.dao.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 8.10 elia

    /**
     * 查询用户是否存在
     * @param account
     * @return
     */
    @Select("select * from user where account = #{account}")
    @ResultMap("userResultMap")
    User selectByAccount(@Param("account") String account);



    /**
     * 查询昵称是否存在
     * @param username
     * @return 0:不存在 其他:昵称已存在
     */
    @Select("select count(*) from user where username = #{username}")
    User selectByUsername(@Param("username") String username);

    /**
     * 尝试登录
     * @param account
     * @param password
     * @return
     */
    @Select("select * from user where account = #{account} and password = #{password}")
    @ResultMap("userResultMap")
    User selectByAccAndPwd(@Param("account") String account, @Param("password") String password);




    //8.10 悠悠球

    /**
     * 注册  将用户已填写的数据封装成user对象,再调用此方法
     * @param user
     * @return
     */
    @Insert("insert into user values " +
            "(null, #{username}, #{account}, #{password}, #{isAdmin}, #{status})")
    boolean addUser(User user);

    /**
     * 更新用户数据
     * 将更改后的用户数据封装成user对象,再调用此方法
     * @param user
     */
    @Update("UPDATE user SET " +
            "username = #{username},password = #{password}," +
            "is_admin = #{is_admin},status = #{status} " +
            "where account=#{account}")
    void updateByAccount(User user);

    /**
     * 根据当前用户id查询关注的人id
     * @param id
     * @return
     */
    @Select("select follow_id from user_follow where id =#{id}")
    @ResultMap("followResultMap")
    List<Integer> selectAllFollowsByAccount(@Param("id") Integer id);

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id =#{id}")
    @ResultMap("userResultMap")
    User selectById(@Param("id") Integer id);
}
