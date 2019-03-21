package com.hhf.common.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhf.common.branch.model.Branch;
import com.hhf.common.branch.service.BranchService;
import com.hhf.common.exception.BusinessException;
import com.hhf.common.response.constant.ResponseCodeConstant;
import com.hhf.common.response.pojo.CommonResponse;
import com.hhf.common.user.dto.UserRegionDTO;
import com.hhf.common.user.model.RbacUser;
import com.hhf.common.user.model.UserRegion;
import com.hhf.common.user.service.RbacUserService;
import com.hhf.common.user.service.UserRegionService;
import com.hhf.common.utils.BeanUtils;
import io.swagger.annotations.Api;
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
 * @since 2019-03-20
 */
@Api(value = "/api/userRegion", description = "用户审核机构管理模块")
@RestController
@RequestMapping("/api/userRegion")
public class UserRegionController {

    @Autowired
    UserRegionService userRegionService;
    @Autowired
    BranchService branchService;
    @Autowired
    RbacUserService rbacUserService;

    @RequestMapping(value = "/addUserRegion",method = RequestMethod.POST)
    public CommonResponse<String> addUserRegion(@RequestBody UserRegionDTO userRegionDTO) {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("BRANCH_CODE",userRegionDTO.getRegionCode());
        Branch branch = branchService.getOne(queryWrapper);
        if (branch ==  null) {
            throw new BusinessException("该审核机构不存在!");
        }
        userRegionDTO.setRegionName(branch.getBranchName());
        QueryWrapper<RbacUser> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_LOGIN_CODE",userRegionDTO.getUserLoginCode());
        RbacUser rbacUser = rbacUserService.getOne(wrapper);
        if (rbacUser ==  null) {
            throw new BusinessException("该用户不存在!");
        }
        QueryWrapper<UserRegion> userRegionQueryWrapper = new QueryWrapper<>();
        userRegionQueryWrapper.eq("USER_LOGIN_CODE",userRegionDTO.getUserLoginCode());
        userRegionQueryWrapper.eq("REGION_CODE",userRegionDTO.getRegionCode());
        UserRegion userRegion = userRegionService.getOne(userRegionQueryWrapper);
        if (userRegion != null) {
            throw new BusinessException("用户该审核机构已经存在");
        }
        UserRegion region = new UserRegion();
        BeanUtils.copyProperties(userRegionDTO, region);
        boolean result = userRegionService.save(region);
        if (result) {
            return CommonResponse.buildRespose4Success(null, "添加成功");
        }
        return CommonResponse.buildRespose4Fail(ResponseCodeConstant.ERROR, "添加失败");
    }
}

