package gulustar.service;

import gulustar.dao.pojo.User;

import java.util.List;

public interface UserService {

    //8.10 得进

    /**
     * 登录
     * @return
     */
    User login(String account,String password);

    //8.10 棒棒糖

    /**
     * 注册
     * @param user
     * @return
     */
    boolean registe(User user);

    /**
     * 用户关注的人
     */
    List<User> selectAllFollowByAccount(User user);

}
