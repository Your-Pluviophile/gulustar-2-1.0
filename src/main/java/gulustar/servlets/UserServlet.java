package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.LoginInfos;
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
        LoginInfos loginInfos = JSON.parseObject(params, LoginInfos.class);
        String account = loginInfos.getAccount();
        String password = loginInfos.getPassword();

        //登录业务处理
        User user = userService.login(account, password);

        //将结果返回 如果用户不存在会返回null
        String jsonString = JSON.toJSONString(user);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

        //如果用户存在,将用户存到session中,避免重复登录
        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
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
