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
@ApiModel(value = "TaskAssigeeReqDTO", description = "领取任务请求对象")
public class TaskAssigeeReqDTO {

    @ApiModelProperty(value = "任务id", example = "3434")
    private String taskDetailId;
    @ApiModelProperty(value = "审批人信息")
    HandlerDTO handlerDTO;
}
