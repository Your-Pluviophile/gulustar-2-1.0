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
     * 根据条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取输入流
        BufferedReader reader = req.getReader();
        //获取查询条件
        String condition = reader.readLine();
        //获取查询条件转对象
        Blog blog = JSON.parseObject(condition, Blog.class);
        //查询
        List<Blog> blogs = blogService.selectCondition(blog);
        //将结果转为JSON
        String jsonString = JSON.toJSONString(blogs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
