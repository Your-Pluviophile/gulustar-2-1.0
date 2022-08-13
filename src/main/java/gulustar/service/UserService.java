package gulustar.service;

import gulustar.dao.pojo.User;

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

}
