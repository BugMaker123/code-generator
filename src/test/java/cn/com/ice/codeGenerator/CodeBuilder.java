package cn.com.ice.codeGenerator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author legen
 * Date 2023-02-20
 */
public class CodeBuilder {


    /**
     * 要生成的文件的根路径
     */
    public static String FILE_PATH="cn.com.ice.codeGenerator";

    /**
     * 包名-默认为空
     */
    private static String MODULE_NAME = "";


    public static void main(String[] args) throws InterruptedException {
        build();
    }

    /**
     * 生成的逻辑
     */
    public static void build(){
        // 1.定义AutoGenerator对象
        AutoGenerator autoGenerator = getAutoGenerator();
        // 2.定义全局配置
        autoGenerator.setGlobalConfig(getGlobalConfig());
        // 3.定义数据源属性
        autoGenerator.setDataSource(getDataSourceConfig());
        // 4.配置包名-要生成的路径
        autoGenerator.setPackageInfo(getPackageConfig());
        // 5.自定义配置
        autoGenerator.setCfg(getInjectionConfig());
        // 6.定义模板
        autoGenerator.setTemplate(getTemplateConfig());
        // 7.定义策略
        autoGenerator.setStrategy(getStrategyConfig());
        // 8.开始执行
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    /**
     * 定义AutoGenerator对象
     */
    public static AutoGenerator getAutoGenerator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("legen");
        //是否打开输出目录
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        return mpg;
    }

    /**
     * 全局配置
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("legen");
        // 是否打开输出目录
        globalConfig.setOpen(false);
        // 实体属性 Swagger2 注解
        globalConfig.setSwagger2(true);
        return globalConfig;
    }

    /**
     * 配置数据库连接
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(new DatabaseInfo().getDatabaseInfo());
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(DatabaseInfo.USER_NAME);
        dataSourceConfig.setPassword(DatabaseInfo.PASSWORD);
        return dataSourceConfig;
    }

    /**
     * 生成路径配置
     * @return
     */
    public static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        MODULE_NAME=scanner("模块名");
        packageConfig.setModuleName(MODULE_NAME);
        packageConfig.setParent(FILE_PATH);
        return packageConfig;
    }
    /**
     * 自定义配置
     * @return
     */
    public static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        // 自定义配置会被优先输出
        fileOutConfigList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String projectPath = System.getProperty("user.dir");
                return projectPath + "/src/main/resources/mybatis/mapper/"+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 定义模板信息
     */
    public static TemplateConfig getTemplateConfig(){
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setService("/templates/service.java");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        templateConfig.setController("/templates/controller.java");
        templateConfig.setXml("/templates/mapper.xml");
        return templateConfig;
    }

    /**
     * 定义策略配置
     * @return
     */
    public static StrategyConfig getStrategyConfig(){
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategyConfig.setSkipView(true);//跳过视图
        // 设置超类
        //strategyConfig.setSuperEntityClass("com.neko.seed.base.entity.BaseEntity");
        // 设置通用字段
        strategyConfig.setSuperEntityColumns("isDelete","createUser","createTime","modifyUser","modifyTime");
        //启用 lombok
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 输入表名来进行创建策略
        strategyConfig.setInclude(scanner("表名"));
        //strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix(MODULE_NAME + "_");
        return strategyConfig;
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}

