package com.hhf.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhf.task.dto.AuditTaskDTO;
import com.hhf.task.dto.AuditTaskReqDTO;
import com.hhf.task.dto.TaskAssigeeReqDTO;
import com.hhf.task.model.UwTaskDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
public interface UwTaskDetailService extends IService<UwTaskDetail> {

    /**
     *
     * 任务领取
     * @param taskAssigeeReqDTO
     */
   void receiveTask(TaskAssigeeReqDTO taskAssigeeReqDTO);

    /**
     *
     * 审核任务
     * @param auditTaskReqDTO
     */
    boolean auditTask(AuditTaskReqDTO auditTaskReqDTO);

}
