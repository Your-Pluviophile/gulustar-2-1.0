package gulustar.mapper;

import gulustar.pojo.Blog;
import gulustar.pojo.History;
import gulustar.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *  用户增删改查
 */
public interface UserMapper {

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    @Select("select * from user where account = #{account}")
    @ResultMap("userResultMap")
    User selectByAccount(@Param("account") String account);

    /**
     * 根据昵称查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User selectByUsername(@Param("username") String username);

    /**
     * 根据账号和密码查询用户
     * @param account
     * @param password
     * @return
     */
    @Select("select * from user where account = #{account} and password = #{password}")
    @ResultMap("userResultMap")
    User selectByAccAndPwd(@Param("account") String account, @Param("password") String password);

    /**
     * 添加用户到数据库
     * @param user
     * @return
     */
    @Insert("insert into user values " +
            "(null, #{username}, #{account}, #{password}, #{isAdmin}, #{status})")
    boolean addUser(User user);

    /**
     * 更新指定id用户数据
     * 将更改后的用户数据封装成user对象,再调用此方法
     * @param user
     */
    @Update("UPDATE user SET " +
            "username = #{username},password = #{password}," +
            "is_admin = #{is_admin},status = #{status} " +
            "where id = #{id}")
    void updateById(User user);

    /**
     * 根据当前用户id查询关注的人id
     * @param id
     * @return
     */
    //select * from user where id in(select follow_id from user_follow where user_id = #{id})
    @Select("select follow_id from user_follow where user_id = #{id}")
    @ResultMap("followResultMap")
    List<Integer> selectAllFollowsByAccount(@Param("id") Integer id);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @ResultMap("userResultMap")
    User selectById(@Param("id") Integer id);

    /**
     * 添加用户浏览记录
     * @param history
     * @return
     */
    @Insert("insert into user_history values "+
            "(#{userid},#{blogid})")
    boolean addUserHistory(History history);

    /**
     * 根据当前用户id所有历史记录
     * @param id
     * @return
     */
    @Select("select b.* from user_history as uh , blog b where uh.blog_id = b.id  and uh.user_id = #{id};")
    @ResultMap("historyResultMap")
    List<Blog> selectAllHistoryByAccount(@Param("id") Integer id);
}
