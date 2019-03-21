package com.hhf.common.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hhf.common.entity.base.BaseVersionEntity;
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
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user_region")
@ApiModel(value="UserRegion对象", description="")
public class UserRegion extends BaseVersionEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户登录代码")
    @TableField("USER_LOGIN_CODE")
    private String userLoginCode;

    @ApiModelProperty(value = "审核机构代码")
    @TableField("REGION_CODE")
    private String regionCode;

    @ApiModelProperty(value = "审核机构名称")
    @TableField("REGION_NAME")
    private String regionName;

    @ApiModelProperty(value = "核保等级")
    @TableField("LEVEL")
    private String level;

}
