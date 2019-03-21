package com.hhf.flowable.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: huhaifeng
 * @Date: 2018/9/30
 * @Time: 14:20
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@ApiModel(value = "HandlerDTO", description = "处理人")
public class HandlerDTO implements Serializable {

    private static final long serialVersionUID = -6050124242830390431L;
    @ApiModelProperty(value = "处理人工号 设置为用户登录code 唯一", example = "1")
    private String handlerNo;
    @ApiModelProperty(value = "处理人分公司代码", example = "10")
    private String handlerBranchCode;
    @ApiModelProperty(value = "处理人姓名", example = "用户2")
    private String handlerName;
    @ApiModelProperty(value = "处理人等级", example = "10")
    private String handlerLevel;

}
