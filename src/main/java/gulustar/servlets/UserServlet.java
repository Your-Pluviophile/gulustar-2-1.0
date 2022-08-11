package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.User;
import gulustar.service.UserService;
import gulustar.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    // 8.10 清风

    private UserService userService = new UserServiceImpl();

    /**
     * 登录
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取账号和密码
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        String[] loginInfos = JSON.parseObject(params, String[].class);
        String account = loginInfos[0];
        String password = loginInfos[1];

        //登录业务处理
        User user = userService.login(account, password);

        //判断用户是否存在
        if (user != null) {
            //存在说明登录成功
            resp.getWriter().write("登录成功！");
            //将用户存到session中
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        } else {
            resp.getWriter().write("登录失败,请检查用户名密码！");
        }
    }

    /**
     * 注册
     */
    public void registe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取账号和密码
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        boolean registe = userService.registe(account, username, password);
        if (registe){
            //注册成功
            resp.getWriter().write("注册成功！");
        }else {
            resp.getWriter().write("注册失败！");
        }

    }
}
