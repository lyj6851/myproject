package com.hhf.flowable.service;

import com.hhf.flowable.dto.CompleteTaskReqDTO;
import com.hhf.flowable.dto.HandlerDTO;
import com.hhf.flowable.dto.HandlerReqDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
public interface FlowableService {

    /**
     * 流程部署
     * @param multipartFile
     */
    public void deployment(MultipartFile multipartFile);

    /**
     *  启动流程
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    void startProcess(Long id);

    /**
     * 完成任务
     * 提供供业务调用的方法
     * 我这边只是模拟调用
     * @param completeTaskReqDTO
     * @return
     */
    boolean completeTask(CompleteTaskReqDTO completeTaskReqDTO);

    /**
     * 设置处理人
     * @param handlerReqDTO
     */
    void setAssignee(HandlerReqDTO handlerReqDTO);

}
