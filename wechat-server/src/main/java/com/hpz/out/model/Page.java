package com.hpz.out.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页对象 对分页的基本数据进行一个简单的封装
 * Created by mao on 2015/6/8.
 */
public class Page<T> implements java.io.Serializable,Cloneable{

    private static final long serialVersionUID = -9200442566817527918L;

    /**
     * 排序的字段
     */
    private String sort;

    /**
     * 升降序方式
     * asc
     * desc
     * 默认为升序排序
     */
    private String order;

    /**
     * 页码，默认是第一页
     */
    private int page = 1;
    /**
     * 每页显示的记录数，默认是15
     */
    private int pageSize = 15;
    /**
     * 总记录数
     */
    private int records = 1;
    /**
     * 总页数
     */
    private int total = 1;
    /**
     * 当前页对应的数据中的记录
     */
    private List<T> rows;
    /**
     * 其他的参数我们把它分装成一个Map对象
     */
    private Map<String, Object> params = new HashMap<String, Object>();

    /**
     * 当前页码
     * @return int
     */
    public int getPage() {
        return this.page;
    }

    /**
     * 设置当前页码，当前页码为大于等于1的正整数
     * @param  page
     */
    public void setPage(int page) {
        //当前页码大于1，对其进行设置，否则一律为默认页码
        if(page >= 1){
            this.page = page;
        }
    }

    /**
     * 每页显示的记录数
     * @return int
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置每页显示记录数
     * @param  pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 总记录数
     * @return int
     */
    public int getRecords() {
        return this.records;
    }

    /**
     * 设置总记录数
     * @param records
     */
    public void setRecords(int records) {
        this.records = records;
        //在设置总记录数的时候计算出对应的总页数
        int totalPage = (records % pageSize == 0) ? (records / pageSize) : (records / pageSize + 1);
        this.setTotal(totalPage);
    }

    /**
     * 总页数
     * @return int
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * 对外不提供设置总页数的方法
     * 总页数在 设置总记录数setTotalRecord(int)时已经计算完毕且已经设置
     * 往往客户端只需要得到总页数
     * 详见:com.hx.common.Page.setTotalRecord(int totalRecord)
     * @param total
     */
    private void setTotal(int total) {
        this.total = total;
    }

    /**
     * 返回查询结果集
     * @return java.util.List<T>
     */
    public List<T> getRows() {
        return this.rows;
    }

    /**
     * 设置查询结果集
     * @param rows
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 查询参数 Map
     * @return java.util.Map
     */
    @JsonIgnore
    public Map<String, Object> getParams() {
        return this.params;
    }

    /**
     * 设置查询参数
     * @param  params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * 是否有首页
     */
    public boolean hasFirstPage() {
        //当前页码大于1时，表示有首页
        return this.page > 1 ? true : false;
    }

    /**
     * 是否有上一页
     */
    public boolean hasPrevious() {
        //当前页码大于1时，表示有上一页
        return this.page > 1 ? true : false;
    }

    /**
     * 是否有下一页
     */
    public boolean hasNext() {
        //当前页码小于总页数时，表示有下一页
        return this.page < this.total ? true : false;
    }

    /**
     * 是否有尾页
     */
    public boolean hasEndPage() {
        //当前页码小于总页数时，表示有尾页
        return this.page < this.total ? true : false;
    }

    /**
     * 上一页页码
     */
    public int prevPageNo() {
        return this.page - 1;
    }

    /**
     * 下一页页码
     */
    public int nextPageNo() {
        return this.page + 1;
    }

    @JsonIgnore
    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @JsonIgnore
    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(page).append(", pageSize=")
                .append(pageSize).append(", rows=").append(rows)
                .append(", total=").append(total)
                .append(", records=").append(records).append("]");
        return builder.toString();
    }

    /**
     * 深度克隆对象
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
