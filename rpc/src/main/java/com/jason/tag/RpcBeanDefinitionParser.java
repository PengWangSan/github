package com.jason.tag;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class RpcBeanDefinitionParser implements BeanDefinitionParser {

	private Class beanClass;

	public RpcBeanDefinitionParser(Class beanClass) {
		super();
		this.beanClass = beanClass;
	}

	public BeanDefinition parse(Element element, ParserContext parserContext) {

		return parse(element, parserContext, beanClass);
	}

	private BeanDefinition parse(Element element, ParserContext parserContext, Class beanClass) {

		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		String id = element.getAttribute("id");
		String interfaceClass = element.getAttribute("interfaceClass");
		String ref = element.getAttribute("ref");
		beanDefinition.getPropertyValues().addPropertyValue("id",id);
		beanDefinition.getPropertyValues().addPropertyValue("interfaceClass",interfaceClass);
		beanDefinition.getPropertyValues().addPropertyValue("ref",ref);
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}

}
