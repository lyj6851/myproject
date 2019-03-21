package com.hhf.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.hhf.common.utils.JsonUtil;
import com.hhf.demo.model.Person;
import com.hhf.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huhaifeng
 * @since 2019-03-18
 */
@Api(value = "/demo/person", description = "测试服务接口")
@RestController
@RequestMapping("demo/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @ApiOperation(value = "添加person")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public JSONObject addPerson() {
        Person user = new Person();
        user.setUserName("huahifeng");
        user.setUserPwd("hhf");
        user.setCreatedBy("胡海丰");
        user.setCreatedOn(new Date());
        boolean result = personService.save(user);
        return JsonUtil.success(result);
    }

}

