package com.hhf.flowable.constant;

import com.hhf.flowable.dto.ProcessDispatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huhaifeng
 * @description 自动调度线程本地变量
 */
public class ProcessThreadLocal {

    private static final Logger logger = LoggerFactory.getLogger(ProcessThreadLocal.class);
    private static InheritableThreadLocal<List<String>> PROCESS_RUN_TASKID_LIST = new InheritableThreadLocal();
    private static InheritableThreadLocal<ProcessDispatchDTO> PROCESS_TARGET_HANDLE = new InheritableThreadLocal();

    public static void addRunTaskId(String taskId) {
        List<String> taskIdSet = PROCESS_RUN_TASKID_LIST.get();
        if (taskIdSet != null) {
            taskIdSet.add(taskId);
        } else {
            taskIdSet = new ArrayList<>();
            taskIdSet.add(taskId);
        }
        PROCESS_RUN_TASKID_LIST.set(taskIdSet);
    }

    public static List<String> getRunTaskIdList() {
        return PROCESS_RUN_TASKID_LIST.get();
    }

    public static void removeRunTaskIdList() {
        PROCESS_RUN_TASKID_LIST.remove();
    }

    public static void setProcessDispatchDTO(ProcessDispatchDTO processDispatchDTO) {
        logger.info("添加ThreadLocal,处理人为:{}",processDispatchDTO);
        PROCESS_TARGET_HANDLE.set(processDispatchDTO);
    }

    public static ProcessDispatchDTO getProcessDispatchDTO() {
        logger.info("获取ThreadLocal,处理人为:{}",PROCESS_TARGET_HANDLE.get());
        return PROCESS_TARGET_HANDLE.get();
    }

    public static void removeProcessDispatchDTO() {
        logger.info("清除ThreadLocal,处理人为:{}",getProcessDispatchDTO());
        PROCESS_TARGET_HANDLE.remove();
    }
}
