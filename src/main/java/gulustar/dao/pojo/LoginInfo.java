package gulustar.dao.pojo;

/**
 *  封装登录信息 包括账号密码
 */
public class LoginInfo {
    private String account;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
