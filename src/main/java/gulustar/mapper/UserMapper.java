package gulustar.mapper;

import gulustar.pojo.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    // 8.10 elia

    /**
     * 查询用户是否存在
     * @param account
     * @param password
     * @return
     */
    @Select("select * from user where account = #{account}")
    @ResultMap("userResultMap")
    User selectByAccount(@Param("account") String account, @Param("password") String password);


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
    User login(@Param("account") String account, @Param("password") String password);




    //8.10 悠悠球

    /**
     * 注册  将用户已填写的数据封装成user对象,再调用此方法
     * @param user
     * @return
     */
    @Insert("insert into user values " +
            "(#{null},#{username}, #{account},#{password},#{is_admin},#{status})")
    void register(User user);

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


}
