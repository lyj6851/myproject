package com.hhf.common.branch.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhf.common.branch.dto.BranchReqDTO;
import com.hhf.common.branch.model.Branch;
import com.hhf.common.branch.service.BranchService;
import com.hhf.common.exception.BusinessException;
import com.hhf.common.response.pojo.CommonResponse;
import com.hhf.common.user.dto.RbacUserDTO;
import com.hhf.common.user.model.RbacUser;
import com.hhf.common.utils.BeanUtils;
import com.hhf.common.utils.JsonUtil;
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
@Api(value = "/api/branch", description = "分公司管理模块")
@RestController
@RequestMapping("/api/branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @ApiOperation(value = "添加分公司")
    @RequestMapping(value = "/addBranch",method = RequestMethod.POST )
    public CommonResponse<Boolean> addBranch(@RequestBody BranchReqDTO branchReqDTO) {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("BRANCH_CODE",branchReqDTO.getBranchCode());
        Branch branch = branchService.getOne(queryWrapper);
        if (branch != null) {
            throw new BusinessException("该分公司已经存在！");
        }
        Branch b = new Branch();
        BeanUtils.copyProperties(branchReqDTO,b);
        return CommonResponse.buildRespose4Success(branchService.save(b),"添加成功");
    }
}

