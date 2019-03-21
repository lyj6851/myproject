package com.hhf.common.branch.model;

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
 * @since 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("b_branch")
@ApiModel(value="Branch对象", description="分公司对象")
public class Branch extends BaseVersionEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "分公司代码")
    @TableField("BRANCH_CODE")
    private String branchCode;

    @ApiModelProperty(value = "分公司名称")
    @TableField("BRANCH_NAME")
    private String branchName;

}
