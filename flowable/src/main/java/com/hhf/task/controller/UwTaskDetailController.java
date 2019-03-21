package com.hhf.task.controller;


import com.hhf.common.response.pojo.CommonResponse;
import com.hhf.task.dto.AuditTaskDTO;
import com.hhf.task.dto.AuditTaskReqDTO;
import com.hhf.task.dto.TaskAssigeeReqDTO;
import com.hhf.task.service.UwTaskDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Api(value = "/api/uwTaskDetail", description = "UWTaskDetail任务管理模块")
@RestController
@RequestMapping("/api/uwTaskDetail")
public class UwTaskDetailController {

    @Autowired
    UwTaskDetailService uwTaskDetailService;

    /**
     *  领取任务
     * @param taskAssigeeReqDTO
     * @return
     */
    @ApiOperation("领取任务")
    @RequestMapping(value = "/receiveTask",method = RequestMethod.POST)
    public CommonResponse<String> receiveTask(@RequestBody TaskAssigeeReqDTO taskAssigeeReqDTO) {
        uwTaskDetailService.receiveTask(taskAssigeeReqDTO);
        return CommonResponse.buildRespose4Success(null,"领取成功");
    }

    /**
     *  审核任务
     * @param auditTaskReqDTO
     * @return
     */
    @ApiOperation("审核任务")
    @RequestMapping(value = "/auditTask",method = RequestMethod.POST)
    public CommonResponse<Boolean> auditTask(@RequestBody AuditTaskReqDTO auditTaskReqDTO) {
        boolean result = uwTaskDetailService.auditTask(auditTaskReqDTO);
        return CommonResponse.buildRespose4Success(result,"审核成功");
    }

    /**
     *  审核任务，并指定下一任务的审核人
     * @param auditTaskDTO
     * @return
     */
    @ApiOperation("审核任务,并指定下一任务的审核人")
    @RequestMapping(value = "/completeTask",method = RequestMethod.POST)
    public CommonResponse<Boolean> completeTask(@RequestBody AuditTaskDTO auditTaskDTO) {
        boolean result = uwTaskDetailService.auditTask(auditTaskDTO);
        return CommonResponse.buildRespose4Success(result,"审核成功");
    }
}

