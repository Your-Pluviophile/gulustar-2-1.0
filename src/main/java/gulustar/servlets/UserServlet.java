package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.LoginInfo;
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
     * 登录 用获取到的账号密码信息查询、返回查询结果 结果不为null说明登录成功
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取账号和密码
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        LoginInfo loginInfos = JSON.parseObject(params, LoginInfo.class);
        String account = loginInfos.getAccount();
        String password = loginInfos.getPassword();

        //登录业务处理
        User user = userService.login(account, password);

        //将结果返回 用户不存在则为null
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
     * 注册方法：把封装的user对象发给业务层处理,给前端返回布尔值结果
     */
    public void registe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取账号和密码
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params, User.class);

        //注册业务处理
        boolean registe = userService.registe(user);
        if (registe){
            resp.getWriter().write("OK");
        }else {
            resp.getWriter().write("注册失败");
        }
    }
}
