package cn.itsource.gogou.query;

public class BaseQuery {
    private String keyword;

    //当前显示第几页 默认显示第一页【主要用来接收前端传递的请求参数】
    private int pageNum = 1;

    //每页显示多少行，默认10行【主要用来接收前端传递的请求参数】
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
