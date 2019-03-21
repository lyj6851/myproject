package com.hhf.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-03-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Person对象", description="")
public class Person extends BaseVersionEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "personid")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    @ApiModelProperty(value = "用户名",example = "huhaifeng")
    @TableField("user_name")
    private String userName;
    @ApiModelProperty(value = "密码",example = "123456")
    @TableField("user_pwd")
    private String userPwd;


}
