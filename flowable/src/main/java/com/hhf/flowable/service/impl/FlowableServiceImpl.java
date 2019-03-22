package com.hhf.flowable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fndsoft.flowable.service.ProcessDefinitionService;
import com.hhf.common.exception.BusinessException;
import com.hhf.common.utils.JacksonUtils;
import com.hhf.flowable.constant.ProcessConstant;
import com.hhf.flowable.constant.ProcessThreadLocal;
import com.hhf.flowable.dto.CompleteTaskReqDTO;
import com.hhf.flowable.dto.HandlerDTO;
import com.hhf.flowable.dto.HandlerReqDTO;
import com.hhf.flowable.dto.ProcessDispatchDTO;
import com.hhf.flowable.service.FlowableService;
import com.hhf.task.mapper.UwTaskDetailMapper;
import com.hhf.task.model.UwTask;
import com.hhf.task.model.UwTaskDetail;
import com.hhf.task.service.UwTaskService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Service
public class FlowableServiceImpl implements FlowableService {

    private static final Logger log = LoggerFactory.getLogger(FlowableServiceImpl.class);
    @Autowired
    ProcessDefinitionService processDefinitionService;
    @Autowired
    UwTaskService uwTaskService;
    @Autowired
    UwTaskDetailMapper uwTaskDetailMapper;
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;

    @Override
    public void deployment(MultipartFile multipartFile) {
        processDefinitionService.createProcessDefinition(ProcessConstant.TENANTID, multipartFile);
    }

    @Override
    public void startProcess(Long id) {
        UwTask uwTask = uwTaskService.getById(id);
        UwTaskDetail uwTaskDetail = new UwTaskDetail();
        BeanUtils.copyProperties(uwTask, uwTaskDetail, "id");
        //设置业务号   UwTask表的主键id
        uwTaskDetail.setBusinessNo(String.valueOf(uwTask.getId()));
        Map map = com.fndsoft.flowable.util.BeanUtils.objectToMap(uwTaskDetail);
        //启动流程后，进入工作流监听，调用taskCreated方法
        processDefinitionService.startProcessDefinition(ProcessConstant.ProcessEnum.PROCESS_ID.getLable(), map, ProcessConstant.TENANTID);
    }

    @Override
    public boolean completeTask(CompleteTaskReqDTO completeTaskReqDTO) {
        QueryWrapper<UwTaskDetail> queryWrapper = new QueryWrapper<UwTaskDetail>();
        queryWrapper.eq("PROCESS_TASK_ID", completeTaskReqDTO.getTaskProcessId());
        UwTaskDetail uwTaskDetail = uwTaskDetailMapper.selectOne(queryWrapper);
        if (uwTaskDetail == null) {
            throw new BusinessException("任务流程id为" + completeTaskReqDTO.getTaskProcessId() + "的任务不存在!!");
        }
        Task task = taskService.createTaskQuery().taskId(completeTaskReqDTO.getTaskProcessId()).singleResult();
        //设置流程变量
        Map<String, Object> map = new HashMap<String, Object>();
        //注意 此处任务等级和处理人等级必须为数字类型   用于工作流图中等级的比较
        map.put("currentHandlerLevel", Integer.valueOf(completeTaskReqDTO.getHandleLevel()));
        map.put("auditCode", completeTaskReqDTO.getAuditCode());
        map.put("auditReason", completeTaskReqDTO.getAuditReason());
        map.put("taskLevel", Integer.valueOf(uwTaskDetail.getTaskLevel()));
        if (completeTaskReqDTO.getHandlerDTO() != null) {
            log.info("审核任务时设置了下一任务处理人，下一任务处理人为：{}", JacksonUtils.obj2json(completeTaskReqDTO.getHandlerDTO()));
            //设置本地变量   将下一任务处理人放入本地变量中，交给监听器处理
            taskService.setVariables(task.getId(), com.hhf.common.utils.BeanUtils.objectToMap(completeTaskReqDTO.getHandlerDTO()));
            ProcessDispatchDTO processDispatchDTO = new ProcessDispatchDTO();
            processDispatchDTO.setHandlerDTO(completeTaskReqDTO.getHandlerDTO());
            log.info("--将下一任务处理人放入线程变量中--");
            ProcessThreadLocal.setProcessDispatchDTO(processDispatchDTO);
        }
        taskService.complete(completeTaskReqDTO.getTaskProcessId(), map);
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        if (pi == null) {
            return true;
        }
        return false;
    }

    @Override
    public void setAssignee(HandlerReqDTO handlerReqDTO) {
        ProcessDispatchDTO processDispatchDTO = new ProcessDispatchDTO();
        processDispatchDTO.setHandlerDTO(handlerReqDTO.getHandlerDTO());
        log.info("领取任务，将处理人放入线程变量中，处理人为：{}",JacksonUtils.obj2json(handlerReqDTO.getHandlerDTO()));
        ProcessThreadLocal.setProcessDispatchDTO(processDispatchDTO);
        taskService.setAssignee(handlerReqDTO.getProcessTaskId(),handlerReqDTO.getHandlerDTO().getHandlerNo());
    }
}
