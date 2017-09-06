package com.jason.tag;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RpcClientBean implements FactoryBean, ApplicationContextAware {

	public Object getObject() throws Exception {

		Object object = act.getBean(ref);

		InvocationHandler rpcHanlder = new RpcInvocationHandler();

		Object o = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
				rpcHanlder);

		return o;
	}

	@SuppressWarnings("rawtypes")
	public Class getObjectType() {

		Class c = null;
		try {
			c = Class.forName(interfaceClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return c;
	}

	public boolean isSingleton() {
		return true;
	}

	private String id;

	private String interfaceClass;

	private String ref;

	private ApplicationContext act;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterfaceClass() {
		return interfaceClass;
	}

	public void setInterfaceClass(String interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.act = arg0;
	}

}
