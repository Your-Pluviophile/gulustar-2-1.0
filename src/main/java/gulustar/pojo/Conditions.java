package gulustar.pojo;

/**
 *  用来封装查询条件
 */
public class Conditions {
    private String keyword;             //查询关键词
    private String category;            //查询分类
    private Integer total;              //总页数
    private Integer currentPage;        //当前页数
    private Integer size;               //一页显示的博客条数

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
