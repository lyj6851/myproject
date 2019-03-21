package com.hhf.common.user.mapper;

import com.hhf.common.user.dto.DispatchUserResDTO;
import com.hhf.common.user.model.RbacUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Mapper
public interface RbacUserMapper extends BaseMapper<RbacUser> {

    /**
     * 自动调度获取能审核该单子的用户
     * @param branchCode
     * @param taskCurrentLevel
     * @param branchMaxLevel
     * @return
     */
    List<DispatchUserResDTO> getDispatchUsers(@Param("branchCode") String branchCode,@Param("taskCurrentLevel") Integer taskCurrentLevel,@Param("branchMaxLevel") Integer branchMaxLevel,@Param("headMaxLevel") Integer headMaxLevel);
}
