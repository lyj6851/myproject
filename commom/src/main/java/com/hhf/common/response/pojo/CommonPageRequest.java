package com.hhf.common.response.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author huhaifeng
 * @description 分页查询请求对象
 */
@ApiModel("分页查询请求对象")
public class CommonPageRequest extends CommonRequest {
    @ApiModelProperty(
            value = "第几页,页码从1开始",
            required = false,
            example = "1"
    )
    private int pageIndex;
    @ApiModelProperty(
            value = "每页数据量",
            required = false,
            example = "100"
    )
    private int pageSize;
    @ApiModelProperty(
            value = "排序描述字符串：\n     * <li>多个字段以英文逗号隔开；</li>\n     * <li>每个字段分为字段名和排序方式(ASC表示升序，DESC 表示降序)，展示方式如classescode:ASC</li>",
            required = false,
            example = ""
    )
    private String sort;

    public CommonPageRequest() {
        this.pageIndex = 1;
        this.pageSize = 100;
    }

    public CommonPageRequest(int pageIndex, int pageSize) {
        this(pageIndex, pageSize, (String)null);
    }

    public CommonPageRequest(int pageIndex, int pageSize, String sort) {
        this.pageIndex = 1;
        this.pageSize = 100;
        if (pageIndex < 1) {
            throw new IllegalArgumentException("pageIndex不能小于1!");
        } else if (pageSize < 1) {
            throw new IllegalArgumentException("pageSize不能小于1!");
        } else {
            this.pageIndex = pageIndex;
            this.pageSize = pageSize;
            this.sort = sort;
        }
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    @ApiModelProperty("当前页面第一条记录在总记录数中的下标，从0开始")
    public int getOffset() {
        return (this.pageIndex - 1) * this.pageSize;
    }

    public String getSort() {
        return this.sort;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public CommonPageRequest next() {
        return new CommonPageRequest(this.pageIndex + 1, this.pageSize, this.sort);
    }

    public CommonPageRequest previousOrFirst() {
        return this.hasPrevious() ? new CommonPageRequest(this.pageIndex - 1, this.pageSize, this.sort) : this;
    }

    public CommonPageRequest first() {
        return new CommonPageRequest(1, this.pageSize, this.sort);
    }

    public boolean hasPrevious() {
        return this.pageIndex > 1;
    }
}
