package com.rebellion.todo_list_api.dao;

import java.util.List;

public class PaginationDao {
    private List<TaskOutDao> data;
    private int page;
    private Long limit;
    private Long total;

    public PaginationDao() {
    }

    public List<TaskOutDao> getData() {
        return data;
    }

    public void setData(List<TaskOutDao> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}