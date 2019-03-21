package com.hhf.common.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体基础类，只有本模块中有维护功能的才需要继承本类
 * @author Kent Chen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "BaseEntity", description="BaseEntity")
@MappedSuperclass
public abstract class BaseEntity extends BaseCreateEntity implements Serializable {

  @ApiModelProperty(value="最后操作人Id", required = false, example = "test_user")
  @Column(name="updated_by")
  @LastModifiedBy
  @TableField(fill = FieldFill.UPDATE)
  protected String updatedBy;

  @ApiModelProperty(value="最后修改时间", required = false, example = "2017-4-17 15:00:12")
  @Column(name="updated_on")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.UPDATE)
  protected Date updatedOn;

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }

  @Override
  public String toString() {
    return "BaseEntity{" +
            "createdBy='" + createdBy + '\'' +
            ", updatedBy='" + updatedBy + '\'' +
            ", createdOn=" + createdOn +
            ", updatedOn=" + updatedOn +
            '}';
  }
}
