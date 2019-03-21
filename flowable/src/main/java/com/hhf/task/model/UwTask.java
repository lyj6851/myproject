package com.hhf.task.model;

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
@ApiModel(value="UwTask对象", description="")
public class UwTask extends BaseVersionEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "申请号")
    @TableField("APP_NO")
    private String appNo;

    @ApiModelProperty(value = "分公司代码")
    @TableField("BRANCH_CODE")
    private String branchCode;

    @ApiModelProperty(value = "任务等级")
    @TableField("TASK_LEVEL")
    private String taskLevel;

    @ApiModelProperty(value = "已核等级")
    @TableField("CURRENT_LEVEL")
    private String currentLevel;

    @ApiModelProperty(value = "终审等级")
    @TableField("FINAL_LEVEL")
    private String finalLevel;

    @ApiModelProperty(value = "任务状态  Y:已终审  N:未终审")
    @TableField("TASK_STATUS")
    private String taskStatus;

    @ApiModelProperty(value = "核保代码	1：通过 2：拒保 3：退回")
    @TableField("AUDIT_CODE")
    private String auditCode;

    @ApiModelProperty(value = "核保原因")
    @TableField("AUDIT_REASON")
    private String auditReason;

    @ApiModelProperty(value = "当前处理人")
    @TableField("HANDLER_NO")
    private String handlerNo;

    @ApiModelProperty(value = "当前处理人等级")
    @TableField("HANDLER_LEVEL")
    private String handlerLevel;

    @ApiModelProperty(value = "当前处理人分公司")
    @TableField("HANDLER_BRANCH_CODE")
    private String handlerBranchCode;

    @ApiModelProperty(value = "当前处理人名称")
    @TableField("HANDLER_NAME")
    private String handlerName;

    @ApiModelProperty(value = "保额")
    @TableField("INSURED_AMOUNT")
    private String insuredAmount;


}
