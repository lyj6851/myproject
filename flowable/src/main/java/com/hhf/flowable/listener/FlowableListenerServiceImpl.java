package com.hhf.flowable.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fndsoft.flowable.listener.FlowableAllListener;
import com.fndsoft.flowable.service.ListenerService;
import com.hhf.common.exception.BusinessException;
import com.hhf.common.user.dto.DispatchUserResDTO;
import com.hhf.common.user.mapper.RbacUserMapper;
import com.hhf.common.utils.BeanUtils;
import com.hhf.common.utils.JacksonUtils;
import com.hhf.flowable.constant.ProcessConstant;
import com.hhf.flowable.constant.ProcessThreadLocal;
import com.hhf.flowable.dto.HandlerDTO;
import com.hhf.flowable.dto.ProcessDispatchDTO;
import com.hhf.task.mapper.UwTaskDetailHisMapper;
import com.hhf.task.mapper.UwTaskDetailMapper;
import com.hhf.task.mapper.UwTaskMapper;
import com.hhf.task.model.UwTask;
import com.hhf.task.model.UwTaskDetail;
import com.hhf.task.model.UwTaskDetailHis;
import com.hhf.task.service.UwTaskDetailService;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: PGL
 * @Date: 2018/10/9
 * @Time: 10:14
 * To change this template use File | Settings | File Templates.
 * Description: 工作台监听事件服务实现
 */
public class FlowableListenerServiceImpl implements ListenerService {

    private static final Logger log = LoggerFactory.getLogger(FlowableAllListener.class);
    @Autowired
    UwTaskDetailService uwTaskDetailService;
    @Autowired
    UwTaskDetailMapper uwTaskDetailMapper;
    @Autowired
    RbacUserMapper rbacUserMapper;
    @Autowired
    UwTaskDetailHisMapper uwTaskDetailHisMapper;
    @Autowired
    UwTaskMapper uwTaskMapper;

    @Override
    public void engineCreated(FlowableEvent event) {
        return;
    }

    @Override
    public void variableCreated(FlowableEvent event) {
        return;

    }

    @Override
    public void variableUpdated(FlowableEvent event) {
        return;

    }

    @Override
    public void variableDeleted(FlowableEvent event) {
        return;


    }


    @Override
    public void taskCreated(FlowableEvent event) {
        org.flowable.common.engine.impl.event.FlowableEntityEventImpl flowableEvent = (org.flowable.common.engine.impl.event.FlowableEntityEventImpl) event;
        TaskEntity task = (TaskEntityImpl) flowableEvent.getEntity();
        log.info("--流程任务创建开始--");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> variablesAll = new HashMap<>(16);
        String taskId = task.getId();
        // 内存中的变量
        Map<String, Object> variablesTask = task.getVariables();
        Map<String, Object> variablesLocalTask = task.getVariablesLocal();
        Map<String, Object> transientVariables = task.getTransientVariables();
        //获取工作流中所有变量
        Map<String, Object> processVariables = null;
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery().processInstanceId(flowableEvent.getProcessInstanceId()).includeProcessVariables().singleResult();
        if (processVariables != null) {
            variablesAll.putAll(processVariables);
        }
        variablesAll.putAll(variablesTask);
        variablesAll.putAll(variablesLocalTask);
        variablesAll.putAll(transientVariables);
        log.info("流程变量{}", variablesAll);
        String assign = task.getAssignee();
        log.info("assign={}", assign);
        //将启动流程时设置的流程变量转成UwTaskDetail对象
        UwTaskDetail uwTaskDetail = JSONObject.parseObject(JSON.toJSONString(variablesAll), UwTaskDetail.class);
        //构造UwTaskDetail对象
        setUwTaskDetail(task, taskId, uwTaskDetail);
        uwTaskDetailService.save(uwTaskDetail);
        log.info("--流程创建结束--");
        ProcessDispatchDTO processDispatchDTO = ProcessThreadLocal.getProcessDispatchDTO();
        if (processDispatchDTO != null && processDispatchDTO.getHandlerDTO() != null) {
            log.info("任务创建时指定任务处理人：{}", JacksonUtils.obj2json(processDispatchDTO.getHandlerDTO()));
            processEngine.getTaskService().setAssignee(taskId, processDispatchDTO.getHandlerDTO().getHandlerNo());
        } else {
            log.info("--开始自动调度--");
            //找能审核该任务的用户
            List<DispatchUserResDTO> dispatchUserResDTOList = rbacUserMapper.getDispatchUsers(uwTaskDetail.getBranchCode(), Integer.valueOf(uwTaskDetail.getCurrentLevel()), ProcessConstant.TaskLevelEnum.BRANCH.getMaxLv(), ProcessConstant.TaskLevelEnum.HEAD_BRANCH.getMaxLv());
            List<DispatchUserResDTO> dispatchUserResult = new ArrayList<DispatchUserResDTO>();
            int initCount = 0;
            if (dispatchUserResDTOList != null && !dispatchUserResDTOList.isEmpty()) {
                //获取任务量最少的用户
                getMinTaskCountUsers(dispatchUserResDTOList, dispatchUserResult, initCount);
            }
            log.info("任务量最少的用户为：{}", JacksonUtils.obj2json(dispatchUserResult));
            if (dispatchUserResult.size() > 0) {
                Random random = new Random();
                //当前随机自动分配的人
                DispatchUserResDTO dispatchUserResDTO = dispatchUserResult.get(random.nextInt(dispatchUserResult.size()));
                log.info("自动调度最终用户为：{}", JacksonUtils.obj2json(dispatchUserResDTO));
                log.info("自动调度指定的处理人为：{}", dispatchUserResDTO.getUserLoginCode());
                HandlerDTO handlerDTO = new HandlerDTO();
                handlerDTO.setHandlerNo(dispatchUserResDTO.getUserLoginCode());
                handlerDTO.setHandlerLevel(dispatchUserResDTO.getLevel());
                handlerDTO.setHandlerBranchCode(dispatchUserResDTO.getBranchCode());
                handlerDTO.setHandlerName(dispatchUserResDTO.getUserName());
                if (processDispatchDTO == null) {
                    processDispatchDTO = new ProcessDispatchDTO();
                }
                processDispatchDTO.setHandlerDTO(handlerDTO);
                //设置流程线程变量  设置处理人 供后面taskAssined方法调用
                ProcessThreadLocal.setProcessDispatchDTO(processDispatchDTO);
                processEngine.getTaskService().setAssignee(taskId, dispatchUserResDTO.getUserLoginCode());
            } else {
                //没有找到自动调度用户
                log.info("--暂无自动调度人员信息--");
            }
        }
    }

    /**
     * 获取任务量最少的用户
     *
     * @param dispatchUserResDTOList
     * @param dispatchUserResult
     * @param initCount
     */
    private void getMinTaskCountUsers(List<DispatchUserResDTO> dispatchUserResDTOList, List<DispatchUserResDTO> dispatchUserResult, int initCount) {
        for (DispatchUserResDTO dispatchUserResDTO : dispatchUserResDTOList) {
            QueryWrapper<UwTaskDetail> queryWrapper = new QueryWrapper<UwTaskDetail>();
            queryWrapper.eq("HANDLER_NO", dispatchUserResDTO.getUserLoginCode());
            queryWrapper.eq("TASK_STATUS", ProcessConstant.TaskStatusEnum.N.getLable());
            //获取用户名下未审核的任务总数
            int count = uwTaskDetailService.count(queryWrapper);
            log.info("用户名称：{},登录代码：{},名下任务总数：{}", dispatchUserResDTO.getUserName(), dispatchUserResDTO.getUserLoginCode(), count);
            dispatchUserResDTO.setTaskCount(count);
            if (initCount > count) {
                initCount = count;
                log.info("集合清空前数据为：{}", JacksonUtils.obj2json(dispatchUserResult));
                dispatchUserResult.clear();
                log.info("集合清空后数据为：{}", JacksonUtils.obj2json(dispatchUserResult));
                dispatchUserResult.add(dispatchUserResDTO);
            } else if (initCount == count) {
                initCount = count;
                dispatchUserResult.add(dispatchUserResDTO);
            } else {
                if (initCount == 0 && dispatchUserResult.size() == 0) {
                    initCount = count;
                    dispatchUserResult.add(dispatchUserResDTO);
                }
            }
        }
    }

    /**
     * 构造UwTaskDetail对象
     *
     * @param task
     * @param taskId
     * @param uwTaskDetail
     */
    private void setUwTaskDetail(TaskEntity task, String taskId, UwTaskDetail uwTaskDetail) {
        uwTaskDetail.setProcessNodeId(task.getTaskDefinitionKey());
        uwTaskDetail.setProcessNodeName(task.getName());
        uwTaskDetail.setProcessTaskId(taskId);
        uwTaskDetail.setAuditReason(null);
        uwTaskDetail.setAuditCode(null);
        log.info("从变量中转换后的UwTaskDetail对象为：{}", uwTaskDetail);
        // 清除处理人
        task.setVariable("handlerBranchCode", null);
        task.setVariable("handlerLevel", null);
        task.setVariable("handlerName", null);
        task.setVariable("handlerNo", null);
    }


    @Override
    public void processCreated(FlowableEvent event) {
        return;


    }

    @Override
    public void processStarted(FlowableEvent event) {
        return;

    }

    @Override
    public void engineClosed(FlowableEvent event) {
        return;

    }

    @Override
    public void taskAssigned(FlowableEvent event) {
        FlowableEntityEventImpl flowableEvent = (FlowableEntityEventImpl) event;
        TaskEntity task = (TaskEntityImpl) flowableEvent.getEntity();
        log.info("--任务分派开始--");
        //取出任务
        String taskId = task.getId();
        //根据流程id 取出未完成任务详情
        QueryWrapper<UwTaskDetail> queryWrapper = new QueryWrapper<UwTaskDetail>();
        queryWrapper.eq("PROCESS_TASK_ID", taskId);
        queryWrapper.eq("TASK_STATUS", ProcessConstant.TaskStatusEnum.N.getLable());
        UwTaskDetail uwTaskDetail = uwTaskDetailService.getOne(queryWrapper);
        if (uwTaskDetail == null) {
            log.info("流程id为：{},任务不存在！", taskId);
            throw new BusinessException("流程id为：" + taskId + ",任务状态为：" + ProcessConstant.TaskStatusEnum.N.getLable() + "的UwTaskDetail表中任务暂不存在");
        }
        ProcessDispatchDTO processDispatchDTO = ProcessThreadLocal.getProcessDispatchDTO();
        if (processDispatchDTO == null) {
            throw new BusinessException("流程任务线程变量中没有设置当前处理人！！");
        }
        log.info("流程任务线程变量设置的当前处理人为：{}", JacksonUtils.obj2json(processDispatchDTO));
        uwTaskDetail.setHandlerNo(processDispatchDTO.getHandlerDTO().getHandlerNo());
        uwTaskDetail.setHandlerBranchCode(processDispatchDTO.getHandlerDTO().getHandlerBranchCode());
        uwTaskDetail.setHandlerLevel(processDispatchDTO.getHandlerDTO().getHandlerLevel());
        uwTaskDetail.setHandlerName(processDispatchDTO.getHandlerDTO().getHandlerName());
        uwTaskDetailService.updateById(uwTaskDetail);
        log.info("--任务分派结束");
    }


    @Override
    public void dispatchAssigned(FlowableEvent event) {
        return;

    }

    @Override
    public void taskCompleted(FlowableEvent event) {
        log.info("--任务结束开始--");
        FlowableEntityEventImpl flowableEvent = (FlowableEntityEventImpl) event;
        TaskEntity task = (TaskEntityImpl) flowableEvent.getEntity();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> variablesAll = new HashMap<>(16);
        // 内存中的变量
        Map<String, Object> variablesTask = task.getVariables();
        Map<String, Object> variablesLocalTask = task.getVariablesLocal();
        Map<String, Object> transientVariables = task.getTransientVariables();
        //获取变量
        Map<String, Object> processVariables = null;
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery().processInstanceId(flowableEvent.getProcessInstanceId()).includeProcessVariables().singleResult();
        if (processVariables != null) {
            variablesAll.putAll(processVariables);
        }
        variablesAll.putAll(variablesTask);
        variablesAll.putAll(transientVariables);
        variablesAll.putAll(variablesLocalTask);
        log.info("流程变量{}", variablesAll);
        //从流程变量中获取当前处理人等级
        String handleLevel = String.valueOf(variablesAll.get(ProcessConstant.HANDLE_LEVEL));
        //从流程变量中获取当前审核代码
        String auditCode = String.valueOf(variablesAll.get(ProcessConstant.AUDIT_CODE));
        //从流程变量中获取当前审核原因
        String auditReason = String.valueOf(variablesAll.get(ProcessConstant.AUDIT_REASON));
        Map<String, Object> map = new HashMap<String, Object>(16);
        QueryWrapper<UwTaskDetail> queryWrapper = new QueryWrapper<UwTaskDetail>();
        queryWrapper.eq("PROCESS_TASK_ID", task.getId());
        queryWrapper.eq("TASK_STATUS", ProcessConstant.TaskStatusEnum.N.getLable());
        UwTaskDetail uwTaskDetail = uwTaskDetailService.getOne(queryWrapper);
        log.info("当前任务信息为：{}", JacksonUtils.obj2json(uwTaskDetail));
        uwTaskDetail.setTaskStatus(ProcessConstant.TaskStatusEnum.Y.getLable());
        uwTaskDetail.setAuditCode(auditCode);
        uwTaskDetail.setAuditReason(auditReason);
        if (!StringUtils.isEmpty(handleLevel)) {
            uwTaskDetail.setCurrentLevel(handleLevel);
            map.put("currentLevel", handleLevel);
            //判断是否终审
            if (Integer.valueOf(uwTaskDetail.getTaskLevel()) <= Integer.valueOf(handleLevel)) {
                uwTaskDetail.setFinalLevel(handleLevel);
                map.put("finalLevel", handleLevel);
            }
        }
        task.setVariables(map);
        uwTaskDetailService.updateById(uwTaskDetail);
        log.info("--任务结束完成--");
    }

    @Override
    public void processCompleted(FlowableEvent event) {
        FlowableEntityEventImpl flowableEvent = (FlowableEntityEventImpl) event;
        log.info("--流程完成开始--");
        Execution execution = ProcessEngines.getDefaultProcessEngine().getRuntimeService().createExecutionQuery().executionId(flowableEvent.getExecutionId()).singleResult();
        //获取流程变量中的 业务号  终审等级
        String bussinessNo = String.valueOf(ProcessEngines.getDefaultProcessEngine().getRuntimeService().getVariable(execution.getId(), "businessNo"));
        String finalLevel = String.valueOf(ProcessEngines.getDefaultProcessEngine().getRuntimeService().getVariable(execution.getId(), "finalLevel"));
        log.info("execution:{}", execution);
        QueryWrapper<UwTaskDetail> queryWrapper = new QueryWrapper<UwTaskDetail>();
        queryWrapper.eq("BUSINESS_NO", bussinessNo);
        List<UwTaskDetail> uwTaskDetailList = uwTaskDetailMapper.selectList(queryWrapper);
        for (UwTaskDetail uwTaskDetail : uwTaskDetailList) {
            UwTaskDetailHis uwTaskDetailHis = new UwTaskDetailHis();
            BeanUtils.copyProperties(uwTaskDetail, uwTaskDetailHis, "id");
            uwTaskDetailService.removeById(uwTaskDetail.getId());
            uwTaskDetailHisMapper.insert(uwTaskDetailHis);
        }
        UwTask uwTask = uwTaskMapper.selectById(bussinessNo);
        if (uwTask == null) {
            throw new BusinessException("id为" + bussinessNo + "的任务在UwTask表中不存在");
        }
        uwTask.setTaskStatus(ProcessConstant.TaskStatusEnum.Y.getLable());
        uwTask.setFinalLevel(finalLevel);
        uwTaskMapper.updateById(uwTask);
    }


    @Override
    public void entityCreated(FlowableEvent event) {
        return;

    }

    @Override
    public void activityStarted(FlowableEvent event) {
        org.flowable.engine.delegate.event.impl.FlowableActivityEventImpl activityEvent = (org.flowable.engine.delegate.event.impl.FlowableActivityEventImpl) event;
        log.info("activity事件类型： {}", activityEvent.getActivityType());
    }
}
