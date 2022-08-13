package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.Blog;
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
 * 博客管理
 */
@WebServlet("/blog/*")
public class BlogServlet extends BaseServlet {

    private BlogService blogService = new BlogServiceImpl();

    /**
     * 根据分类查询博客
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void queryByClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        //查询
        List<Blog> blogs = blogService.QueryByCategory(categoryId);

        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(blogs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
