package com.hhf.common.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 无当前操作用户的实体基础类，只有创建时间和最后修改时间
 * @author xman
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BaseNoneUserEntity", description="BaseNoneUserEntity")
@MappedSuperclass
public abstract class BaseNoneUserEntity implements Serializable {

  @ApiModelProperty(value="创建时间", required = true, example = "2017-4-17 15:00:12")
  @Column(name="created_on", updatable=false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.INSERT)
  protected Date createdOn;

  @ApiModelProperty(value="最后修改时间", required = false, example = "2017-4-17 15:00:12")
  @Column(name="updated_on")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.UPDATE)
  protected Date updatedOn;


  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }

  @Override
  public String toString() {
    return "BaseNoneUserEntity{" +
            "createdOn=" + createdOn +
            ", updatedOn=" + updatedOn +
            '}';
  }
}
