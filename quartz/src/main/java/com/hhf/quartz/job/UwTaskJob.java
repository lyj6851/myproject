package com.hhf.quartz.job;

import com.hhf.task.service.UwTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author huhaifeng
 * @description 定时跑批，出单
 */
@DisallowConcurrentExecution
public class UwTaskJob implements Job,Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ChickenJob.class);
    private static final long serialVersionUID = 1L;

    @Autowired
    UwTaskService uwTaskService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("--跑批出单开始--");
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        logger.info("任务名称：{}",jobDetail.getJobClass().getName());
        logger.info("任务类名：{}",jobDetail.getJobClass());
        logger.info("任务开始时间：{}",jobExecutionContext.getFireTime());
        logger.info("任务下一次执行时间：{}",jobExecutionContext.getNextFireTime());
        logger.info("任务的Trigger为：{}",jobExecutionContext.getTrigger());
        uwTaskService.getPolicyMessage();
        logger.info("--跑批出单结束--");
    }
}
