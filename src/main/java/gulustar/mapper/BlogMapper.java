package gulustar.mapper;

import gulustar.pojo.Blog;
import gulustar.pojo.Comment;
import gulustar.pojo.Conditions;
import gulustar.pojo.History;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *  博客的增删改查
 */
public interface BlogMapper {

    /**
     * 获取上次插入数据的行的ID auto_increment
     * @return
     */
    @Select("SELECT LAST_INSERT_ID()")
    Integer selectLastInsertId();

    /**
     * 添加一条记录到博客-评论对应关系表
     * @return
     */
    @Insert("insert into blog_comment values(#{blogId}, #{commentId})")
    boolean addCommentRelation(@Param("blogId") Integer blogId, @Param("commentId") Integer commentId);

    /**
     * 添加一条记录到评论表
     * @param comment
     * @return
     */
    @Insert("insert into comment values(null, #{content}, #{userId}, now())")
    boolean addComment(Comment comment);

    /**
     * 查询所有符合id的评论
     * @param ids
     * @return
     */
    List<Comment> selectCommentsByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据ID查询对应博客的评论ID集合
     * @param id
     * @return
     */
    @Select("select comment_id from blog_comment where blog_id = #{id}")
    List<Integer> selectCommentIdList(Integer id);

    /**
     * 根据ID查询博客
     *
     * @return
     */
    @Select("select * from blog where id = #{id}")
    @ResultMap("blogResultMap")
    Blog selectOneBlog(Integer id);

    /**
     * 根据页数(查第多少条到多少条)、分类、关键词查询博客
     * @return
     */
    List<Blog> selectByPageAndCondition(@Param("Conditions") Conditions conditions,
                                          @Param("start") Integer start,
                                          @Param("size") Integer size);

    /**
     * 根据条件查询总条数
     * @param conditions
     * @return
     */
    Integer selectCountByCondition(@Param("conditions") Conditions conditions);

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
     * @param category
     * @return
     */
    @Select("select * from blog where category = #{category}")
    List<Blog> selectByCategory(@Param("category")String category);

    /**
     * 查询用户收藏的博客
     * @param
     * @return
     */
    List<Blog> selectCollect(@Param("userId") Integer userId, @Param("conditions") Conditions conditions);

    /**
     * 获取收藏博客总数
     * @return
     */
    Integer selectCollectCount(@Param("userId") Integer userId, @Param("conditions") Conditions conditions);

    /**
     * 根据关键词查询
     * @param keyword
     * @return
     */
    List<Blog> selectByKeyword(@Param("keyword") String keyword);

    /**
     * 查看是否有一样的浏览记录
     * @param history
     * @return
     */
    @Select("select * from user_history where user_id =#{userId} and blog_id= #{blogId}")
    @ResultMap("historyResultMap")
    History selectSameHistory(History history);
}
