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
@ApiModel(value = "CompleteTaskReqDTO", description = "完成审核任务请求对象")
public class CompleteTaskReqDTO {

    @ApiModelProperty(value = "流程任务表id", example = "23456789085")
    private String taskProcessId;
    @ApiModelProperty(value = "审核用户等级", example = "3")
    private String handleLevel;
    @ApiModelProperty(value = "审核代码", example = "1")
    private String auditCode;
    @ApiModelProperty(value = "审核原因")
    private String auditReason;
    @ApiModelProperty(value = "调度人名字")
    private String dispatcherName;
    @ApiModelProperty(value = "指定下一任务处理人")
    private HandlerDTO handlerDTO;
}
