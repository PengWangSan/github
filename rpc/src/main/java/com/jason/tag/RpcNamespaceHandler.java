package com.jason.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RpcNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("client", new RpcBeanDefinitionParser(RpcClientBean.class));
	}

}
