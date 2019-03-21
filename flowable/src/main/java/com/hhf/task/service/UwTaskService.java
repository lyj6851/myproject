package com.hhf.task.service;

import com.hhf.task.model.UwTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
public interface UwTaskService extends IService<UwTask> {

    /**
     *  随机造一份报文，模拟出单
     * @return
     */
    public void getPolicyMessage();
}
