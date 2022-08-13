package gulustar.mapper;

import gulustar.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
/*
更新博客
 */
    @Update("UPDATE blog SET " +
            "user_id = #{user_id},category= #{category}," +
            "title = #{title},description= #{description} ," +
            "content= #{content},likes= #{likes}," +
            "release_date= #{releaseDate},modify_date= now()," +
            "collected = #{colleted},STATUS = #{status} " +
            "where id=#{id}")
    void updateBlog(Blog blog);
/*
添加博客
 */
    @Insert("insert into blog values " +
            "(null, #{userId}, #{category}, #{title}, #{description}, #{content}, #{likes}," +
            " #{collected}, #{status}, now(), now())")
    boolean addBlog(Blog blog);
    /*
    删除博客
     */
    @Delete("delete from blog where id=#{id}")
    boolean deleteBlog(Integer blogId);

    @Select("select * from blog where category = #{categoryId}")
    List<Blog> selectByCategory(@Param("categoryId")Integer categoryId);

    @Select("select b.* from user_collection uc,  blog b where uc.blog_id = b.id  and uc.user_id = #{id};")
    @ResultMap("blogResultMap")
    List<Blog> selectCollect(Integer id);
}
