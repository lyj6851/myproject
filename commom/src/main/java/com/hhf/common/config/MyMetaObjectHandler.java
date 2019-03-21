package com.hhf.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**  自定义填充公共 name 字段  */
public class MyMetaObjectHandler implements MetaObjectHandler {

    private String getCurrentUserCode(){
//        CustomUserContextHandler customUserContextHandler = SpringContextHolder.getBean(CustomUserContextHandler.class);
//        //读取当前session中的当前用户
//        Optional<String> currentUserOptional =  customUserContextHandler.getCurrentUserCode();
//        String currentUser = "System";
//        if(currentUserOptional.isPresent()){
//            currentUser = currentUserOptional.get();
//        }

        return "胡海丰";
    }

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String currentUser =  this.getCurrentUserCode();

        // 当前操作人和操作时间
        Object createdBy = getFieldValByName("createdBy", metaObject);//mybatis-plus版本2.0.9+
        if (createdBy == null) {
            setFieldValByName("createdBy", currentUser, metaObject);//mybatis-plus版本2.0.9+
        }
        Object createdOn = getFieldValByName("createdOn", metaObject);//mybatis-plus版本2.0.9+
        if (createdOn == null) {
            setFieldValByName("createdOn", new Timestamp(System.currentTimeMillis()), metaObject);//mybatis-plus版本2.0.9+
        }
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        String currentUser =  this.getCurrentUserCode();
        //mybatis-plus版本2.0.9+
        Object updatedBy = getFieldValByName("updatedBy", metaObject);//mybatis-plus版本2.0.9+
        if (updatedBy == null) {
            setFieldValByName("updatedBy", currentUser, metaObject);
        }
        Object updatedOn = getFieldValByName("updatedOn", metaObject);//mybatis-plus版本2.0.9+
        if (updatedOn == null) {
            setFieldValByName("updatedOn", new Timestamp(System.currentTimeMillis()), metaObject);
        }

    }
}
