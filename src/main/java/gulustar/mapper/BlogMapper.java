package gulustar.mapper;

import gulustar.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *  博客的增删改查
 */
public interface BlogMapper {

    /**
     * 查询所有博客
     * @return: gulustar.pojo.Blog[]
     * @Author: elia
     * @Condition: finished
     * @date: 2022/8/11
     */
    @Select("select * from blog")
    @ResultMap("blogResultMap")
    List<Blog> selectAll();

    /**
     * 更新指定博客
     * @param blog
     */
    @Update("UPDATE blog SET " +
            "user_id = #{user_id},category= #{category}," +
            "title = #{title},description= #{description} ," +
            "content= #{content},likes= #{likes}," +
            "release_date= #{releaseDate},modify_date= now()," +
            "collected = #{colleted},STATUS = #{status} " +
            "where id=#{id}")
    void updateBlog(Blog blog);

    /**
     * 添加博客
     * @param blog
     * @return
     */
    @Insert("insert into blog values " +
            "(null, #{userId}, #{category}, #{title}, #{description}, #{content}, #{likes}," +
            " #{collected}, #{status}, now(), now())")
    boolean addBlog(Blog blog);

    /**
     * 删除博客
     * @param blog
     * @return
     */
    @Delete("delete from blog where id=#{id}")
    boolean deleteBlog(Blog blog);

    /**
     * 根据分类查询博客
     * @param categoryId
     * @return
     */
    @Select("select * from blog where category = #{categoryId}")
    List<Blog> selectByCategory(@Param("categoryId")Integer categoryId);

    /**
     * 查询用户收藏的博客
     * @param id
     * @return
     */
    @Select("select b.* from user_collection uc,  blog b where uc.blog_id = b.id  and uc.user_id = #{id};")
    @ResultMap("blogResultMap")
    List<Blog> selectCollect(Integer id);

    /**
     * 根据关键词查询
     * @param keyword
     * @return
     */
    List<Blog> selectByKeyword(@Param("keyword") String keyword);
}
