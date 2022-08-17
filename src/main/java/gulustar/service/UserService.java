package gulustar.service;

import gulustar.pojo.*;

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
     * @param
     * @return
     */
    BlogPageBean selectHistory(Integer userId, Conditions conditions);

    /**
     * 用户收藏，取消收藏
     * @param
     * @return
     */
    boolean collectionBlog(Integer userId, String blogId);

    boolean deleteCollection(Integer userId, String blogId);

    /**
     * 收藏 Id
     * @param userId
     * @return
     */
    List<Integer> selectCollectBlogIds(Integer userId);
}
