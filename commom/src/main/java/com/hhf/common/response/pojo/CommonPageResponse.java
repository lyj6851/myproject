package com.hhf.common.response.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author huhaifeng
 * @description 分页查询返回结果对象
 */
@ApiModel("分页查询返回结果对象")
public class CommonPageResponse<R> extends CommonResponse<List<R>> {
    @ApiModelProperty("分页查询请求对象，页码从1开始")
    private CommonPageRequest pageRequest;
    @ApiModelProperty(
            value = "查询结果的总记录数",
            required = true,
            example = "250"
    )
    private long totalElements;

    public CommonPageResponse() {
        this.totalElements = 0L;
        this.setSuccess(true);
        this.setCode("000000");
        this.setMessage("操作成功！");
    }

    public CommonPageResponse(List<R> content, CommonPageRequest pageRequest, long totalElements) {
        this();
        this.setData(content);
        this.pageRequest = pageRequest;
        this.totalElements = totalElements;
    }

    public List<R> getData() {
        return (List)super.getData();
    }

    private int getPageIndex() {
        return this.pageRequest == null ? 1 : this.pageRequest.getPageIndex();
    }

    private int getPageSize() {
        return this.pageRequest == null ? this.getCurrentElements() : this.pageRequest.getPageSize();
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    @ApiModelProperty("总页数")
    public int getTotalPages() {
        return this.pageRequest == null ? 1 : (int)Math.ceil((double)this.totalElements / (double)this.getPageSize());
    }

    @ApiModelProperty("当前页的记录数")
    public int getCurrentElements() {
        return CollectionUtils.isEmpty(this.getData()) ? 0 : this.getData().size();
    }

    public boolean hasPrevious() {
        return this.getPageIndex() > 1;
    }

    public boolean hasNext() {
        return this.getPageIndex() < this.getTotalPages();
    }

    @ApiModelProperty("是否为第一页，页码从1开始")
    public boolean isFirst() {
        return !this.hasPrevious();
    }

    @ApiModelProperty("是否为最后一页")
    public boolean isLast() {
        return !this.hasNext();
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public <S> CommonPageResponse<S> map(Converter<? super R, ? extends S> converter) {
        return new CommonPageResponse(this.getConvertedContent(converter), this.pageRequest, this.totalElements);
    }

    protected <S> List<S> getConvertedContent(Converter<? super R, ? extends S> converter) {
        Assert.notNull(converter, "Converter must not be null!");
        List<R> data = this.getData();
        if (CollectionUtils.isEmpty(data)) {
            return Collections.EMPTY_LIST;
        } else {
            List<S> result = new ArrayList(data.size());
            Iterator<R> var3 = data.iterator();

            while(var3.hasNext()) {
                R element = var3.next();
                result.add(converter.convert(element));
            }

            return result;
        }
    }

    public CommonPageRequest getPageRequest() {
        return this.pageRequest;
    }

    public void setPageRequest(CommonPageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }
}

