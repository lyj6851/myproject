package com.hhf.common.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhf.common.branch.model.Branch;
import com.hhf.common.branch.service.BranchService;
import com.hhf.common.exception.BusinessException;
import com.hhf.common.response.pojo.CommonResponse;
import com.hhf.common.user.dto.RbacUserDTO;
import com.hhf.common.user.model.RbacUser;
import com.hhf.common.user.service.RbacUserService;
import com.hhf.common.utils.BeanUtils;
import com.hhf.common.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Api(value = "/api/user", description = "用户管理模块")
@RestController
@RequestMapping("/api/user")
public class RbacUserController {

    @Autowired
    RbacUserService rbacUserService;
    @Autowired
    BranchService branchService;

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/addRbacUser", method = RequestMethod.POST)
    public CommonResponse<Boolean> addRbacUser(@RequestBody RbacUserDTO rbacUserDTO) {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("BRANCH_CODE", rbacUserDTO.getBranchCode());
        Branch branch = branchService.getOne(queryWrapper);
        if (branch == null) {
            throw new BusinessException("该分公司不存在！");
        }
        QueryWrapper<RbacUser> wrapper = new QueryWrapper<RbacUser>();
        wrapper.eq("USER_LOGIN_CODE", rbacUserDTO.getUserLoginCode());
        RbacUser rbacUser = rbacUserService.getOne(wrapper);
        if (rbacUser != null) {
            throw new BusinessException("该用户已经存在！");
        }
        rbacUserDTO.setBranchName(branch.getBranchName());
        RbacUser user = new RbacUser();
        BeanUtils.copyProperties(rbacUserDTO, user);
        return CommonResponse.buildRespose4Success(rbacUserService.save(user), "添加成功");
    }
}

