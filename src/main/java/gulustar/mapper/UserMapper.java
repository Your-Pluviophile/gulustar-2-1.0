package gulustar.mapper;

import gulustar.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *  用户增删改查
 */
public interface UserMapper {

    //---------------------棒棒糖-------------------------------
    //更新历史记录时间  先查询再更新 应该要写查询和更新2个方法
    //---------------------棒棒糖-------------------------------

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
    @Insert("insert into `user` values " +
            "(null, #{username}, null, #{account}, #{password}, #{isAdmin}, #{status})")
    boolean addUser(User user);

    /**
     * 更新指定id用户数据
     * 将更改后的用户数据封装成user对象,再调用此方法
     * @param user
     */
    @Update("UPDATE `user` SET " +
            "username = #{username}," +
            "image_url = #{imageUrl} " +
            "where id = #{id}")
    boolean updateById(User user);

    /**
     * 根据当前用户id查询关注的人id(本次项目不实装此功能、未测试)
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
    @Select("select * from `user` where id = #{id}")
    @ResultMap("userResultMap")
    User selectById(@Param("id") Integer id);

    /**
     * 添加用户浏览记录
     * @param history
     * @return
     */
    @Insert("insert into user_history values "+
            "(#{userId}, #{blogId}, now())")
    boolean addUserHistory(History history);

    /**
     * 更新用户浏览记录时间
     * @return
     */
    @Update("update user_history set browse_time = now() where user_id = #{userId} and blog_id = #{blogId}")
    boolean updateUserHistory(History history);

    /**
     * 根据当前用户id所有历史记录
     * @param
     * @return
     */
    List<Blog> selectAllHistoryByAccount(@Param("userId") Integer userId, @Param("conditions")Conditions conditions);

    /**
     * 获取历史记录总数
     * @return
     */
    Integer selectHistoryCount(@Param("userId") Integer userId, @Param("conditions")Conditions conditions);

    /**
     * 用户添加收藏
     * @param userId
     * @param blogId
     * @return
     */
    @Insert("insert into user_collection values(#{userId}, #{blogId})")
    boolean collectBlog(@Param("userId") Integer userId, @Param("blogId") Integer blogId);

    /**
     * 获取用户收藏博客的ID集合
     * @return
     */
    @Select("select blog_id from user_collection where user_id = #{userId}")
    List<Integer> selectCollectBlogIds(Integer userId);

    /*
    取消收藏
     */
    @Delete("delete from user_collection where user_id=#{id} and blog_id= #{id}")
    boolean deleteCollection(Integer userId,Integer blogId);

}
