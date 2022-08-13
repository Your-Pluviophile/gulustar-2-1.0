package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.dao.pojo.Blog;
import gulustar.dao.pojo.User;
import gulustar.service.BlogService;
import gulustar.service.impl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 博客管理
 */
@WebServlet("/blog/*")
public class BlogServlet extends BaseServlet {

    private BlogService blogService = new BlogServiceImpl();

    /**
     * 获取所有博客：拦截请求，返回博客集合
     */
    public void getAllBlogs(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取博客集合
        List<Blog> blogs = blogService.getAllBlogs();

        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(blogs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 根据分类查询博客: 接收分类ID参数.直接交给业务层处理,最后返回博客对象集合
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void queryByCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryId = req.getParameter("categoryId");
        //查询
        List<Blog> blogs = blogService.queryByCategory(categoryId);

        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(blogs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 查询根据用户id收藏的博客
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void queryCollect(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取session中用户id
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            resp.getWriter().write("请先登录！");
            return;
        }
        List<Blog> blogs = blogService.selectCollect(user.getId());
        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(blogs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
