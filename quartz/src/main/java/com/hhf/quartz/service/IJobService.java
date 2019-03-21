package com.hhf.quartz.service;

import com.hhf.quartz.entity.QuartzEntity;

import java.util.List;
public interface IJobService {
	
    List<QuartzEntity> listQuartzEntity(QuartzEntity quartz, Integer pageNo, Integer pageSize);
    
    Long listQuartzEntity(QuartzEntity quartz);	
}
