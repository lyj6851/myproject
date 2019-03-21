package com.hhf.task.dto;

import com.hhf.flowable.dto.HandlerDTO;
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
@ApiModel(value = "AuditTaskReqDTO", description = "审核任务请求对象")
public class AuditTaskReqDTO {

    @ApiModelProperty(value = "任务id", example = "23456789085")
    private String taskDetailId;
    @ApiModelProperty(value = "审核用户等级", example = "3")
    private String handleLevel;
    @ApiModelProperty(value = "审核代码", example = "1")
    private String auditCode;
    @ApiModelProperty(value = "审核原因")
    private String auditReason;
}
