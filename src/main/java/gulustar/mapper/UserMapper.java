package gulustar.mapper;

import gulustar.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    // 8.10 elia
//    @Select()
    User login();


    //8.10 悠悠球
    boolean registe();
}
