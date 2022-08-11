package gulustar.servlets;

import gulustar.service.UserService;
import gulustar.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    // 8.10 清风

    private UserService userService = new UserServiceImpl();

    /**
     * 登录
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    }

    /**
     * 注册
     */
    public void registe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }
}
