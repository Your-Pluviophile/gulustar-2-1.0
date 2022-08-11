package gulustar.pojo;

import java.util.List;

public class User {
    private Integer id;                     //用户ID
    private String username;                //昵称
    private String account;                 //登录的时候输入的账号
    private String password;                //密码
    private Integer isAdmin;                //是管理员
    private List<Integer> collection;       //对应的收藏表
    private List<Integer> follow;           //关注的人
    private List<Integer> history;          //历史浏览记录
    private Integer status;                 //用户状态(正常/已注销)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Integer> getCollection() {
        return collection;
    }

    public void setCollection(List<Integer> collection) {
        this.collection = collection;
    }

    public List<Integer> getFollow() {
        return follow;
    }

    public void setFollow(List<Integer> follow) {
        this.follow = follow;
    }

    public List<Integer> getHistory() {
        return history;
    }

    public void setHistory(List<Integer> history) {
        this.history = history;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
