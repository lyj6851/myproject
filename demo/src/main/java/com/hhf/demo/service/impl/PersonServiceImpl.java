package com.hhf.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhf.demo.mapper.PersonMapper;
import com.hhf.demo.model.Person;
import com.hhf.demo.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-18
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
