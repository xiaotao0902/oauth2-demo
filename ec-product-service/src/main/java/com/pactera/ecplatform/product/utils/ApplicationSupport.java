package com.pactera.ecplatform.product.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class ApplicationSupport implements DisposableBean, ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static String getParamVal(String paramKey){
        return applicationContext.getEnvironment().getProperty(paramKey);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @SuppressWarnings("static-access")
	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
   public void destroy() throws Exception {
        applicationContext = null;
    }

}
