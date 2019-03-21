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
@ApiModel(value="UserRegionDTO对象", description="")
public class UserRegionDTO extends BaseVersionEntity {

    @ApiModelProperty(value = "用户登录代码")
    private String userLoginCode;

    @ApiModelProperty(value = "审核机构代码")
    private String regionCode;

    @ApiModelProperty(value = "审核机构名称")
    private String regionName;

    @ApiModelProperty(value = "审核等级")
    private String level;
}
