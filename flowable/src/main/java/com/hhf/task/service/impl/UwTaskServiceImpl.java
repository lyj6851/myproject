package com.hhf.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhf.common.branch.mapper.BranchMapper;
import com.hhf.common.branch.model.Branch;
import com.hhf.common.exception.BusinessException;
import com.hhf.common.idworker.snowflake.SequenceService;
import com.hhf.common.utils.RandomUtils;
import com.hhf.flowable.service.FlowableService;
import com.hhf.task.mapper.UwTaskMapper;
import com.hhf.task.model.UwTask;
import com.hhf.task.service.UwTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-19
 */
@Service
public class UwTaskServiceImpl extends ServiceImpl<UwTaskMapper, UwTask> implements UwTaskService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UwTaskService uwTaskService;
    @Autowired
    SequenceService sequenceService;
    @Autowired
    FlowableService flowableService;
    @Autowired
    BranchMapper branchMapper;

    @Override
    public void getPolicyMessage() {
        //随机生成投保单号
        String appNo = RandomUtils.getRandomCharacterAndNumber(11);
        String[] taskLevels = {"1", "2", "3", "4", "5","6"};
        //随机生成任务等级 todo 后期根据保额自动定级
        String taskLevel = RandomUtils.getNum(taskLevels);
        //随机生成分公司
        List<Branch> branches = branchMapper.selectList(null);
//        String[] branchCodes = new String[branches.size()];
        //随机生成分公司代码
        String branchCode = RandomUtils.getBranchCode(branches.stream().map(Branch::getBranchCode).collect(Collectors.toList()));
        //随机生成保额
        Integer insuredAmount = RandomUtils.getNum(300, 1000);
        UwTask uwTask = new UwTask();
        uwTask.setId(sequenceService.nextId());
        uwTask.setAppNo(appNo);
        uwTask.setBranchCode(branchCode);
        uwTask.setInsuredAmount(String.valueOf(insuredAmount));
        uwTask.setTaskLevel(taskLevel);
        uwTask.setTaskStatus("N");
        uwTask.setCurrentLevel("0");
        uwTask.setCreatedBy("system");
        uwTask.setCreatedOn(new Date());
        try {
            boolean result = uwTaskService.save(uwTask);
            log.info("保存单子进UwTask返回结果为：{}", result);
            if (result) {
                //提交工作流
                flowableService.startProcess(uwTask.getId());
            }
        } catch (Exception e) {
            log.info("启动流程失败,错误原因为：{}", e);
            throw new BusinessException("启动流程失败,错误原因为：{}", e);
        } finally {
            log.info("出单啦。。。。");
        }
    }
}
