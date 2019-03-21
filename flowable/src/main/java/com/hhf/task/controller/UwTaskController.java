package com.hhf.task.controller;


import com.alibaba.fastjson.JSONObject;
import com.hhf.common.response.constant.ResponseCodeConstant;
import com.hhf.common.response.pojo.CommonResponse;
import com.hhf.task.dto.UwTaskDTO;
import com.hhf.task.mapper.UwTaskMapper;
import com.hhf.task.model.UwTask;
import com.hhf.task.service.UwTaskDetailService;
import com.hhf.task.service.UwTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Api(value = "/api/uwTask", description = "UWTask任务管理模块")
@RestController
@RequestMapping("/api/uwTask")
public class UwTaskController {

    @Autowired
    UwTaskService uwTaskService;

    @ApiOperation("添加任务")
    @RequestMapping(value = "/addUwTask", method = RequestMethod.POST)
    public CommonResponse<String> addUwTask(@RequestBody UwTaskDTO uwTaskDTO) {
        UwTask uwTask = new UwTask();
        BeanUtils.copyProperties(uwTaskDTO, uwTask);
        boolean result = uwTaskService.save(uwTask);
        if (result) {
            return CommonResponse.buildRespose4Success(null, "添加成功");
        }
        return CommonResponse.buildRespose4Fail(ResponseCodeConstant.ERROR, "添加失败");
    }

    @ApiOperation("造报文，随机出单")
    @RequestMapping(value = "/getPolicyMessage", method = RequestMethod.POST)
    public CommonResponse<String> getPolicyMessage() {

        uwTaskService.getPolicyMessage();
        return CommonResponse.buildRespose4Success(null, "出单成功");
    }
}

