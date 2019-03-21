package com.hhf.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus相关配置，参考：http://mp.baomidou.com
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfiguration {
    /**
     * 主键生成器，用于KeySequence注解
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public IKeyGenerator iKeyGenerator() {
        return new H2KeyGenerator();
    }

    /**
     * 逻辑删除功能支持
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ISqlInjector logicSqlInjector() {
        return new LogicSqlInjector();
    }


    /**
     * 分页插件
     * 插件提供二种方言选择：1、默认方言 2、自定义方言实现类，两者均未配置则抛出异常！
     *   overflowCurrent 溢出总页数，设置第一页 默认false
     *   optimizeType Count优化方式 （ 版本 2.0.9 改为使用 jsqlparser 不需要配置 ）
     * 注意!! 如果要支持二级缓存分页使用类 CachePaginationInterceptor 默认！！
     */
    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 公共字段自动填充,可以用来处理审计功能
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public MetaObjectHandler myMetaObjectHandler() {
        return new MyMetaObjectHandler();
    }


//    /**
//     * SQL 执行分析拦截器【 目前只支持 MYSQL-5.6.3 以上版本 】，作用是分析 处理 DELETE UPDATE 语句， 防止小白或者恶意 delete update 全表操作！
//     * 参数：stopProceed 发现执行全表 delete update 语句是否停止执行
//     * 注意！该插件只用于开发环境，不建议生产环境使用
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    @Profile({"local","dev","test"})// 设置 dev test 环境开启
//    public SqlExplainInterceptor sqlExplainInterceptor() {
//        return new SqlExplainInterceptor();
//    }

//    /**
//     * SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
//     * 参数：maxTime SQL 执行最大时长，超过自动停止运行，有助于发现问题。
//     * 参数：format SQL SQL是否格式化，默认false。
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    @Profile({"local","dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }


}
