package gulustar.pojo;

public class History {

    private Integer userid;

    private Integer blogid;

    public History(Integer id, Integer id1) {

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBlogid() {
        return blogid;
    }

    public void setBlogid(int blogid) {
        this.blogid = blogid;
    }

    @Override
    public String toString() {
        return "History{" +
                "userid=" + userid +
                ", blogid=" + blogid +
                '}';
    }
}
