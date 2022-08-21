package gulustar.service;

import gulustar.pojo.*;

import java.util.List;

public interface UserService {

    //8.10 得进

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

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
     * 记录用户浏览
     * @param history
     */
    void addUserHistory(History history);

    /**
     * 用户获取历史信息
     * @param
     * @return
     */
    BlogPageBean selectHistory(Integer userId, Conditions conditions);

    /**
     * 收藏博客
     * @return
     */
    boolean collect(Integer blogId, Integer userId);

    /**
     * 获取用户收藏的博客ID集合
     * @return
     */
    List<Integer> getCollectBlogIds(Integer userId);

    /**
     * 更新用户信息
     * @return
     */
    boolean updateUserInfo(User user);
}
