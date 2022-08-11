package gulustar.mapper;

import gulustar.pojo.Blog;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

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
    Blog[] selectAll();
}
