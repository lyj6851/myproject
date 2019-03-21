package com.hhf.flowable.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huhaifeng
 */
@Data
public class ProcessDispatchDTO {
    private HandlerDTO handlerDTO;
    @ApiModelProperty(value = "调度原因", example = "测试")
    private String dispatchReason;
    @ApiModelProperty(value = "调度人代码")
    private String dispatcherNo;
    @ApiModelProperty(value = "调度人名字")
    private String dispatcherName;
}
