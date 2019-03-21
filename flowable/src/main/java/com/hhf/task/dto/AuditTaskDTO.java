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
@ApiModel(value = "AuditTaskDTO", description = "审核传入对象，并指定下一任务处理人")
public class AuditTaskDTO extends AuditTaskReqDTO{

    @ApiModelProperty(value = "下一任务处理人")
    private HandlerDTO handlerDTO;
}
