package com.jason.tag;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcInvocationHandler implements InvocationHandler {
	
	
	



	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("��ʼԶ�̵���");
		
//		Object result=method.invoke(object, args);
		
		
		String interfact=proxy.getClass().getInterfaces()[0].getTypeName();
		
		System.out.println("�ӿڣ�"+interfact);
		
		String methodName=method.getName();
		
		System.out.println("������"+methodName);
		System.out.println("����Զ�̵��� ");
		
		return "s";
	}

}
