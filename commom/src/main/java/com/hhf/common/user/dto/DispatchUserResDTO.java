package com.hhf.common.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hhf.common.entity.base.BaseVersionEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huhaifeng
 * @since 2019-03-19
 */
@Data
@ApiModel(value="RbacUserDTO对象", description="")
public class DispatchUserResDTO {

    @ApiModelProperty(value = "用户代码")
    private String userCode;

    @ApiModelProperty(value = "用户登录代码")
    private String userLoginCode;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "分公司代码")
    private String branchCode;

    @ApiModelProperty(value = "分公司名称")
    private String branchName;

    @ApiModelProperty(value = "用户状态   1:有效 0:无效")
    private String userStatus;

    @ApiModelProperty(value = "审核机构代码")
    private String regionCode;

    @ApiModelProperty(value = "审核机构名称")
    private String regionName;

    @ApiModelProperty(value = "用户审核等级")
    private String level;

    @ApiModelProperty(value = "名下任务总数")
    private Integer taskCount;
}
