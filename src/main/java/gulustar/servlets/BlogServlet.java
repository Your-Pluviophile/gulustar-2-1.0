package gulustar.servlets;

import com.alibaba.fastjson.JSON;
import gulustar.pojo.*;
import gulustar.service.BlogService;
import gulustar.service.impl.BlogServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 *  拦截并处理博客相关操作请求
 */
@WebServlet("/blog/*")
public class BlogServlet extends BaseServlet {

    private BlogService blogService = new BlogServiceImpl();

    public void upload(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取form表单
        req.setCharacterEncoding("utf-8");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(factory);
        List<FileItem> fileItems = sf.parseRequest(req);

        Blog blog = new Blog();
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        blog.setUserId(userId);

        for (FileItem fileItem : fileItems) {
            //获取form表单里的name
            String fieldName = fileItem.getFieldName();
            //判断是博客内容还是其他
            if ("content".equals(fieldName) || "cover".equals(fieldName)){
                String dir_path = req.getServletContext().getRealPath("user_upload");
                File dir = new File(dir_path);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                String file_name = UUID.randomUUID().toString();
                String suffix = "content".equals(fieldName)? ".txt":".jpg";
                fileItem.write(new File(dir_path, file_name + suffix));

                switch (fieldName){
                    case "content":
                        blog.setContent(dir_path + "/" + file_name + suffix);
                        break;
                    case "cover":
                        blog.setCover(file_name + suffix);
                }
            }else {
                String value = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
                switch (fieldName){
                    case "title":
                        blog.setTitle(value);
                        break;
                    case "category":
                        blog.setCategory(value);
                        break;
                    case "description":
                        blog.setDescription(value);
                        break;
                    default:
                        throw new Exception("表单name不对");
                }
            }
        }

        blogService.addBlog(blog);
    }

    /**
     * 点赞
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addLikes(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取博客ID
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        Integer blogId = JSON.parseObject(params, Integer.class);

        //业务层处理
        boolean addOK = blogService.addLikes(blogId);

        //返回结果
        String json = JSON.toJSONString(addOK);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }

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
     * 获取全部分类
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> allCategories = blogService.getAllCategories();

        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(allCategories);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 根据用户id查询收藏的博客
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

        //业务层处理
        BlogPageBean pageBean = blogService.selectCollect(user.getId(), conditions);

        //将结果转为JSON返回
        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
