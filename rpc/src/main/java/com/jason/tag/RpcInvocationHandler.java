package com.jason.tag;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcInvocationHandler implements InvocationHandler {
	
	
	



	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("开始远程调用");
		
//		Object result=method.invoke(object, args);
		
		
		String interfact=proxy.getClass().getInterfaces()[0].getTypeName();
		
		System.out.println("接口："+interfact);
		
		String methodName=method.getName();
		
		System.out.println("方法："+methodName);
		System.out.println("结束远程调用 ");
		
		return "s";
	}

}
