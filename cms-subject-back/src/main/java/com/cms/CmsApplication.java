package com.cms;

import com.cms.common.MyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhanhaoze
 * @description
 * @date 2021/7/22
 **/
@SpringBootApplication
@MapperScan("com.cms.dao")
@EnableSwagger2
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
        //启动持续更新密钥的操作 每10分钟更新一次密钥
        new MyThread().start();
    }
}
