package com.hhf.common.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体基础类，只有本模块中有维护功能的才需要继承本类.
 * 使用EntityListeners注解来支持jpa auditware自动设置当前操作人和操作时间
 * @author c_xiaomin
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BaseCreateEntity", description="BaseCreateEntity")
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseCreateEntity implements Serializable {

  @ApiModelProperty(value="创建人Id", required = true, example = "test_user")
  @Column(name="created_by", updatable=false)
  @NotNull(message="创建人Id不能为空")
  @CreatedBy
  @TableField(fill = FieldFill.INSERT)
  protected String createdBy;

  @ApiModelProperty(value="创建时间", required = true, example = "2017-4-17 15:00:12")
  @Column(name="created_on", updatable=false)
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull(message="创建时间不能为空")
  @CreatedDate
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.INSERT)
  protected Date createdOn;

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  @Override
  public String toString() {
    return "BaseCreateEntity{" +
            "createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            '}';
  }

}
