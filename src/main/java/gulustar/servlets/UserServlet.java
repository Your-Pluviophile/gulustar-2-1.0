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
     * 获取指定用户
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取用户ID
        int id = Integer.parseInt(req.getParameter("id"));

        //业务层处理
        User user = userService.getUserById(id);

        //将结果转为JSON返回
        String json = JSON.toJSONString(user);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }

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
     * 查询关注的人，把user传给业务层 (本次项目删除此功能)
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
        String keyword = new String(req.getParameter("keyword").getBytes("iso-8859-1"), "utf-8");
        String category = new String(req.getParameter("category").getBytes("iso-8859-1"), "utf-8");
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
        History history = JSON.parseObject(params, History.class);
        userService.addUserHistory(history);
    }

    /**
     * 用户收藏博客
     * @param request
     * @param response
     * @throws IOException
     */
    public void collect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户ID和博客ID
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //只是用来封装  Blog类的userId指的是作者
        Blog getParams = JSON.parseObject(params, Blog.class);
        Integer userId = getParams.getUserId();
        Integer blogId = getParams.getId();

        //业务层处理
        boolean collectOK = userService.collect(userId, blogId);

        //将结果转为JSON返回
        String json = JSON.toJSONString(collectOK);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 获取用户收藏的博客的ID集合
     * @param request
     * @param response
     * @throws IOException
     */
    public void getCollectBlogIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户ID
        int userId = Integer.parseInt(request.getParameter("userId"));

        //业务层查询
        List<Integer> ids = userService.getCollectBlogIds(userId);

        //将结果转为JSON返回
        String json = JSON.toJSONString(ids);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
