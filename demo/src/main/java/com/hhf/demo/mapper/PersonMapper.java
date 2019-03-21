package com.hhf.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhf.demo.model.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-18
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
