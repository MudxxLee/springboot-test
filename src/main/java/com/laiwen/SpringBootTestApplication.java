package com.laiwen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author laiw
 * @date 2022/9/26 14:01
 */
@SpringBootApplication(scanBasePackages = { "com.laiwen" }, exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
public class SpringBootTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootTestApplication.class, args);

    }

}
