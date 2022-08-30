package com.jackfrank.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 代码生成器配置
 * @createTime : 2022/8/28 14:54
 */
public class Generator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/yeb?characterEncoding=utf8";
        String username = "root";
        String password = "Wlj1414213562195";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("jackfrank") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://program//idea-workspace//yeb//yeb-generator//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.jackfrank") // 设置父包名
                            .moduleName("generator") // 设置父包模块名
                            .entity("pojo")
                            .mapper("mapper")
                            .xml("Mapper.xml")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://program//idea-workspace//yeb//yeb-generator//src//main//resources//mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_admin", "t_admin_role", "t_appraise", "t_department", "t_employee", "t_employee_ec", "t_employee_remove",
                                    "t_employee_train", "t_joblevel", "t_mail_log", "t_menu", "t_menu_role", "t_nation", "t_oplog", "t_politics_status",
                                    "t_position", "t_role", "t_salary", "t_salary_adjust", "t_sys_msg", "t_sys_msg_content") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList(); // 设置过滤表前缀
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
