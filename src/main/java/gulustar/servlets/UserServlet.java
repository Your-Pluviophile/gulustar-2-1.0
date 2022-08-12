package gulustar.servlets;

import gulustar.service.UserService;
import gulustar.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet{

    //调用service层的对象
    UserService userService = new UserServiceImpl();

}
