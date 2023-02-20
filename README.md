# code-generator
this is a simple code generator.I will complete it 

这是一个简单的代码生成器,使用freeMarker模板引擎制作
使用了springboot 2.3.4
整合了 mybatis与mysql
使用swagger提供接口文档

当前使用步骤:
1.创建数据库表(数据库连接信息在test目录下的DatabaseInfo中)
2.运行test目录下的CodeBuilder
3.根据提示输入模块名 
如 人员模块输入 tbUser
4.输入表名
tb_user

上述操作完成后即可生成在目录中,修改生成文件的路径在CodeBuilder的path上进行操作即可
