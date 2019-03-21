package com.hhf.common.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 实体版本基础类
 * @author  xman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "BaseVersionEntity", description="BaseEntity")
@MappedSuperclass
public abstract class BaseVersionEntity extends BaseEntity implements Serializable {

  @ApiModelProperty(value="数据操作版本号，用于乐观锁并发控制", example = "1")
  @Column(name="operate_version")
  @Version
  @com.baomidou.mybatisplus.annotation.Version
  protected Long operateVersion = 1L;

  public Long getOperateVersion() {
    return operateVersion;
  }

  public void setOperateVersion(Long operateVersion) {
    this.operateVersion = operateVersion;
  }



  @Override
  public String toString() {
    return "BaseVersionEntity{" +
            "operateVersion=" + operateVersion +
            ", updatedBy='" + updatedBy + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", createdOn=" + createdOn +
            '}';
  }
}
