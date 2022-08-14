package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.*;
import gulustar.service.BlogService;
import gulustar.service.impl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 *  拦截并处理博客相关操作请求
 */
@WebServlet("/blog/*")
public class BlogServlet extends BaseServlet {

    private BlogService blogService = new BlogServiceImpl();

    /**
     * 添加一条评论
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addComment(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取封装了评论内容和博客ID 用户ID的JSON 转为comment对象
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        Comment comment = JSON.parseObject(params, Comment.class);

        //业务层处理 添加记录
        boolean addComment = blogService.addComment(comment);

        //返回结果
        if (addComment){
            resp.getWriter().write("OK");
        }else {
            resp.getWriter().write("寄了");
        }
    }

    /**
     * 获取文章的评论
     */
    public void getContendAndCommentList(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取查询条件
        String blogId = req.getParameter("blogId");
        Integer id = Integer.valueOf(blogId);

        //业务层处理 获取博客对象 包含了评论
        Blog blog = blogService.getOneBlog(id);

        //将结果转为JSON返回
        String json = JSON.toJSONString(blog);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    /**
     * 根据查询条件和页数获取博客集合
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getBlogsByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取查询条件
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        Conditions conditions = JSON.parseObject(params, Conditions.class);

        //调用业务层方法
        BlogPageBean pageBean = blogService.getBlogsByPageAndCondition(conditions);

        //将结果转为JSON返回
        String json = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }

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
        String category = req.getParameter("category");
        //查询
        List<Blog> blogs = blogService.queryByCategory(category);

        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(blogs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 根据关键词查询：获取用户输入，业务层处理后返回符合条件的博客集合
     * @param req
     * @param resp
     * @throws IOException
     */
    public void queryByKeyword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String queryKeyword = req.getParameter("queryKeyword");
        //查询
        List<Blog> blogs = blogService.queryByKeyword(queryKeyword);

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
    public void queryCollectByUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException{
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
