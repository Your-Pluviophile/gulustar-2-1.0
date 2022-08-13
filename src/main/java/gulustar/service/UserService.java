package gulustar.service;

import gulustar.pojo.Blog;
import gulustar.pojo.User;
import gulustar.pojo.History;

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

    void addUserHistory(History history);

    /**
     * 用户获取历史信息
     * @param id
     * @return
     */
    public List<Blog> selectHistory(Integer id);

}
