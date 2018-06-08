package com.ecoo.dtx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

@EnableAutoConfiguration
@SpringBootApplication
@ImportResource(locations={"classpath:hessian-${spring.profiles.active}.xml"})
@EnableJms
public class DtxAdminApplication extends SpringBootServletInitializer {

	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        // 注意这里要指向原先用main方法执行的Application启动类
	        return builder.sources(DtxAdminApplication.class);
	    }
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DtxAdminApplication.class, args);
	}
}
