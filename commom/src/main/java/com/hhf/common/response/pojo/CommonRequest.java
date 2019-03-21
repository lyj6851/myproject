package com.hhf.common.response.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author huhaifeng
 * @description 自定义请求对象
 */
@ApiModel("自定义请求对象")
public class CommonRequest {
    @ApiModelProperty(
            value = "请求上下文信息，可以在网关层面存放token、全局id等信息",
            required = false
    )
    private Map<String, Object> requestContextEx;

    public CommonRequest() {
    }

    public Map<String, Object> getRequestContextEx() {
        return this.requestContextEx;
    }

    public void setRequestContextEx(Map<String, Object> requestContextEx) {
        this.requestContextEx = requestContextEx;
    }
}
