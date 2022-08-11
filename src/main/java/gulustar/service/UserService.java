package gulustar.service;

import gulustar.pojo.User;

public interface UserService {

    //8.10 得进

    /**
     * login
     * @return
     */
    User login(String username,String password);

    //8.10 棒棒糖

    boolean registe(String account, String username, String password);
}
