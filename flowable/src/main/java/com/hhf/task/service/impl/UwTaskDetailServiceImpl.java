package com.hhf.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhf.common.exception.BusinessException;
import com.hhf.flowable.constant.ProcessThreadLocal;
import com.hhf.flowable.dto.CompleteTaskReqDTO;
import com.hhf.flowable.dto.HandlerReqDTO;
import com.hhf.flowable.service.FlowableService;
import com.hhf.task.dto.AuditTaskDTO;
import com.hhf.task.dto.AuditTaskReqDTO;
import com.hhf.task.dto.TaskAssigeeReqDTO;
import com.hhf.task.mapper.UwTaskDetailMapper;
import com.hhf.task.model.UwTaskDetail;
import com.hhf.task.service.UwTaskDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Service
public class UwTaskDetailServiceImpl extends ServiceImpl<UwTaskDetailMapper, UwTaskDetail> implements UwTaskDetailService {

    private static final Logger log = LoggerFactory.getLogger(UwTaskDetailServiceImpl.class);
    @Autowired
    FlowableService flowableService;
    @Autowired
    UwTaskDetailMapper uwTaskDetailMapper;

    @Override
    public void receiveTask(TaskAssigeeReqDTO taskAssigeeReqDTO) {
        //TODO 判断用户是否能领取  领取校验
        if (taskAssigeeReqDTO == null) {
            throw new BusinessException("领取对象不能为空");
        }
        if (StringUtils.isEmpty(taskAssigeeReqDTO.getTaskDetailId())) {
            throw new BusinessException("任务id不能为空");
        }
        if (taskAssigeeReqDTO.getHandlerDTO() == null) {
            throw new BusinessException("指定处理人不能为空");
        }
        UwTaskDetail uwTaskDetail = uwTaskDetailMapper.selectById(taskAssigeeReqDTO.getTaskDetailId());
        if (uwTaskDetail == null) {
            throw new  BusinessException("没有查询到分配处理人的任务");
        }
        HandlerReqDTO handlerReqDTO = new HandlerReqDTO();
        handlerReqDTO.setHandlerDTO(taskAssigeeReqDTO.getHandlerDTO());
        handlerReqDTO.setProcessTaskId(uwTaskDetail.getProcessTaskId());
        flowableService.setAssignee(handlerReqDTO);
    }

    @Override
    public boolean auditTask(AuditTaskReqDTO auditTaskReqDTO) {
        if (auditTaskReqDTO == null) {
            throw new BusinessException("审核对象不能为空");
        }
        UwTaskDetail uwTaskDetail = uwTaskDetailMapper.selectById(auditTaskReqDTO.getTaskDetailId());
        if (uwTaskDetail == null) {
            throw new  BusinessException("没有查询到分配处理人的任务");
        }
        CompleteTaskReqDTO completeTaskReqDTO = new CompleteTaskReqDTO();
        BeanUtils.copyProperties(auditTaskReqDTO,completeTaskReqDTO);
        completeTaskReqDTO.setTaskProcessId(uwTaskDetail.getProcessTaskId());
        //true  终审  false 未终审  调用工作流
        try {
            return flowableService.completeTask(completeTaskReqDTO);
        } finally {
            if (ProcessThreadLocal.getProcessDispatchDTO() != null) {
                log.info("线程变量中待删除的内容为：{}",ProcessThreadLocal.getProcessDispatchDTO());
                ProcessThreadLocal.removeProcessDispatchDTO();
            }
        }
    }
}
