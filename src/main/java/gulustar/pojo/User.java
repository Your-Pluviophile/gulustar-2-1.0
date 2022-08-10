package gulustar.pojo;

import java.util.List;

public class User {
    Integer id;                     //用户ID
    String username;                //昵称
    String account;                 //登录的时候输入的账号
    String password;                //密码
    Integer isAdmin;                //是管理员
    List<Integer> collection;       //对应的收藏表
    List<Integer> follow;           //关注的人
    List<Integer> history;          //历史浏览记录
    Integer status;                 //用户状态(正常/已注销)
}
