package com.hhf.flowable.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: huhaifeng
 * @Date: 2018/9/30
 * @Time: 14:20
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@ApiModel(value = "HandlerReqDTO", description = "设置审批人请求对象")
public class HandlerReqDTO {
    @ApiModelProperty(value = "流程id", example = "3434")
    private String processTaskId;
    @ApiModelProperty(value = "审批人信息")
    HandlerDTO handlerDTO;
}
