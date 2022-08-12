package gulustar.service;

import gulustar.pojo.User;

public interface UserService {
    /**
     * 登录功能
     * @return
     */
    User login();

    /**
     * 注册功能
     * @return
     */
    boolean registe();
}
