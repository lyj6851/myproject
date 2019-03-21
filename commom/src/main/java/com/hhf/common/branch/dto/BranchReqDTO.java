package com.hhf.common.branch.dto;

import com.hhf.common.entity.base.BaseVersionEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huhaifeng
 * @since 2019-03-19
 */
@Data
@ApiModel(value="BranchReqDTO对象", description="添加分公司请求对象")
public class BranchReqDTO extends BaseVersionEntity{

    @ApiModelProperty(value = "分公司代码")
    private String branchCode;

    @ApiModelProperty(value = "分公司名称")
    private String branchName;
}
