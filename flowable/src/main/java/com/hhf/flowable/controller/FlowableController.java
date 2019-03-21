package com.hhf.flowable.controller;

import com.hhf.common.response.pojo.CommonResponse;
import com.hhf.flowable.service.FlowableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Api(value = "/api/flowable", description = "flowable工作流模块")
@RestController
@RequestMapping("/api/flowable")
public class FlowableController {
    @Autowired
    FlowableService flowableService;

    @ApiOperation("部署流程")
    @RequestMapping(value = "/deployment",method = RequestMethod.POST)
    public CommonResponse<String> deployment(@RequestParam("file")MultipartFile multipartFile) {
    flowableService.deployment(multipartFile);
    return CommonResponse.buildRespose4Success(null,"部署成功");
    }
}
