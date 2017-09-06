package com.jason.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jason.bean.User;

public class TestRpc {

	public static void main(String[] args) {

		String xml = "classpath:test.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { xml });
		User user=(User) context.getBean("testRpc");
		
		user.sayHello();
		
	}

}
