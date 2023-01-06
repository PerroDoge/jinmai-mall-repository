package com.mvs.jinmai.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MyGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/jinmai-mall", "root", "root")
                .globalConfig(builder -> {
                    builder.author("ShouHe") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\ShouHe\\IdeaProjects\\mvs-jinmai-mall-parent\\mvs-jinmai-service\\mvs-jinmai-ums\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.mvs.jinmai.ums") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\ShouHe\\IdeaProjects\\mvs-jinmai-mall-parent\\mvs-jinmai-service\\mvs-jinmai-ums\\src\\main\\resources\\com\\mvs\\jinmai")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("ums_Member") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok()
                            .serviceBuilder()
                            .formatServiceImplFileName("%sServiceImpl")
                            .formatServiceFileName("%sService")
                            .mapperBuilder()
                            .enableMapperAnnotation();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
