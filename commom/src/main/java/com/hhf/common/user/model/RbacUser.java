package com.hhf.common.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hhf.common.entity.base.BaseVersionEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_rbac_user")
@ApiModel(value="RbacUser对象", description="")
public class RbacUser extends BaseVersionEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户代码")
    @TableField("USER_CODE")
    private String userCode;

    @ApiModelProperty(value = "用户登录代码")
    @TableField("USER_LOGIN_CODE")
    private String userLoginCode;

    @ApiModelProperty(value = "用户名称")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "电话")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "分公司代码")
    @TableField("BRANCH_CODE")
    private String branchCode;

    @ApiModelProperty(value = "分公司名称")
    @TableField("BRANCH_NAME")
    private String branchName;

    @ApiModelProperty(value = "用户状态   1:有效 0:无效")
    @TableField("USER_STATUS")
    private String userStatus;


}
