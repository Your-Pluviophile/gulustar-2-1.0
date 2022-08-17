package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.*;
import gulustar.service.UserService;
import gulustar.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 *  拦截并处理用户相关操作请求
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();


    //添加收藏  --------------------------悠悠球-------------------------
    //业务层方法叫 collectionBlog

    /**
     * 登录 用获取到的账号密码信息查询、返回查询结果 结果不为null说明登录成功
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取账号和密码
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        User loginInfos = JSON.parseObject(params, User.class);
        String account = loginInfos.getAccount();
        String password = loginInfos.getPassword();

        //登录业务处理
        User user = userService.login(account, password);

        //将结果返回 用户不存在则为null
        String jsonString = JSON.toJSONString(user);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

        //如果用户存在,将用户存到session中,避免重复登录
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        }
    }

    /**
     * 退出登录，清除session的属性
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        //清除session的属性
        session.removeAttribute("user");
        resp.getWriter().write("退出成功");
    }

    /**
     * 注册方法：把封装的user对象发给业务层处理,给前端返回布尔值结果
     */
    public void registe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取账号和密码
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params, User.class);

        //注册业务处理
        boolean registe = userService.registe(user);
        if (registe) {
            resp.getWriter().write("OK");
        } else {
            resp.getWriter().write("注册失败");
        }
    }

    /**
     * 查询关注的人，把user传给业务层
     * @param req
     * @param resp
     * @throws IOException
     */
    public void myFollows(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取当前用户信息
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params, User.class);

        //调用service层，查询关注的人
        List<User> users = userService.selectAllFollowByAccount(user);

        //将结果转为JSON返回
        String json = JSON.toJSONString(users);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    /**
     * 查询所有用户历史记录
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getUserHistory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session中用户id
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.getWriter().write("请先登录！");
            return;
        }
        //获取请求参数 页数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int size = Integer.parseInt(req.getParameter("size"));
        String keyword = req.getParameter("keyword");
        String category = req.getParameter("category");
        //封装为对象
        Conditions conditions = new Conditions();
        conditions.setCurrentPage(currentPage);
        conditions.setSize(size);
        conditions.setKeyword(keyword);
        conditions.setCategory(category);

        //调用service层，查询历史记录
        BlogPageBean pageBean = userService.selectHistory(user.getId(), conditions);

        //将结果转为JSON返回
        String json = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    /**
     * 写入用户历史记录
     * @param request
     * @param response
     */
    public void addUserHistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前用户信息
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params, User.class);
        Blog blog = JSON.parseObject(params, Blog.class);
        History history = new History(user.getId(),blog.getId());
        userService.addUserHistory(history);
    }
    /**
     * 用户添加收藏
     * @param request
     * @param response
     *
     *    UserId  BlogId
     * collectionBlog  deleteCollection
     */
    public void collectionBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前线路
        String blogId = request.getParameter ("blogId");

        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        Integer userId;
        if (user == null){
            response.getWriter().write("请先登录！");
            return;
        }else{
             userId = user.getId ();
        }

        //判断是否添加收藏
        boolean blog = userService.collectionBlog(userId,blogId);
        //将结果转为JSON返回
        String json = JSON.toJSONString(blog);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);

    }

    /**
     * 用户取消收藏
     * @param request
     * @param response
     *
     *    UserId  BlogId
     * collectionBlog  deleteCollection
     */
    public void deleteCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前线路
        String blogId = request.getParameter ("blogId");

        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        Integer userId;
        if (user == null){
            response.getWriter().write("请先登录！");
            return;
        }else{
            userId = user.getId ();
        }

        //判断是否取消收藏
        boolean blog = userService.deleteCollection(userId,blogId);
        //将结果转为JSON返回
        String json = JSON.toJSONString(blog);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
