package com.hhf.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * huhaifeng
 */
public class GeneratorUtil {

    public static  void main(String[]args) {
        GeneratorUtil.generateCode();
    }

    /**
     * 执行生成代码
     */
    public static void generateCode() {
        String packageName = "com.hhf.commom";
        //generateByTables(packageName, "t_student", "t_city", "t_idcard");
        generateByTables(packageName, "b_branch");
    }

    private static void generateByTables(String packageName, String... tableNames) {

        // 数据库信息
//        String dbUrl = "jdbc:mysql://localhost:3306/mybatis-plus?useSSL=true";
        String dbUrl = "jdbc:mysql://localhost:3306/flowable?serverTimezone=GMT%2B8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
//                .setPassword("xfsy2017")
                .setPassword("root")
//                .setDriverName("com.mysql.jdbc.Driver") // mysql 5
                .setDriverName("com.mysql.cj.jdbc.Driver") // mysql 8
                /*.setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public PropertyInfo processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        //.....
                        // 当发现生成的类型并不能满足你的要求时，可以去这里看，然后重写
                    }
                })*/
                ;

        // 配置
        GlobalConfig config = new GlobalConfig()
                .setActiveRecord(false)
                .setAuthor("huhaifeng")
//                .setOutputDir("/Users/fengwenyi/Workspace/file/codeGen")
                .setOutputDir("D:/BaiduNetdiskDownload/flowable/myproject/commom/src/main/java")
                .setFileOverride(true)
                .setActiveRecord(false)// 不需要ActiveRecord特性的请改为false
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setDateType(DateType.ONLY_DATE) //只使用 java.util.date 代替
                .setIdType(IdType.ID_WORKER)
                .setSwagger2(true) // model swagger2
                //.setOpen(true) // 是否打开输出目录
                ;
//                if (!serviceNameStartWithI)
//                    config.setServiceName("%sService");


        StrategyConfig strategyConfig = new StrategyConfig()
                .setCapitalMode(true) // 全局大写命名 ORACLE 注意
                //.setDbColumnUnderline(true)
                .setTablePrefix("B_")// 此处可以修改为您的表前缀(数组)
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel) //数据库表字段映射到实体的命名策略，未指定按照 naming 执行
                .setInclude(tableNames)//修改替换成你需要的表名，多个表名传数组
                //.setExclude(new String[]{"test"}) // 排除生成的表
                .setEntityLombokModel(true) // lombok实体
                .setEntityBuilderModel(false) // 【实体】是否为构建者模型（默认 false）
                .setEntityColumnConstant(false) // 【实体】是否生成字段常量（默认 false）// 可通过常量名获取数据库字段名 // 3.x支持lambda表达式
                //.setLogicDeleteFieldName("is_delete") // 逻辑删除属性名称
                //.setEntityTableFieldAnnotationEnable

                .setSuperEntityClass("com.hhf.common.entity.base.BaseVersionEntity")
                    .setSuperEntityColumns("OPERATE_VERSION","CREATED_BY","UPDATED_BY","UPDATED_ON","CREATED_ON") //自定义基础的Entity类中的公共字段,要大写
                .setControllerMappingHyphenStyle(true) //requestmapping中的url 是否启用驼峰转连字符
                .entityTableFieldAnnotationEnable(true)
                .setRestControllerStyle(true)
                ;

        // 包信息配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent(packageName)
                .setController("controller")
                .setEntity("model")
                .setMapper("mapper")
                .setXml("mapper")
                .setModuleName(packageName)


                /*
                // 共同构建成包名
                .setParent("com.fengwenyi")
                .setModuleName("model")
                */
                ;

        // 执行器
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }
}
