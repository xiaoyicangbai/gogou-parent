package cn.itsource.gogou.util;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
    //总行数
    private Long total=0L;

    public PageList() {
    }

    public PageList(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    //结果集
    private List<T> rows = new ArrayList<>();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
