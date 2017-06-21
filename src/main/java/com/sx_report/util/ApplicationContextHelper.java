package com.sx_report.util;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context = null;
    private static ApplicationContextHelper stools = null;

    public synchronized static ApplicationContextHelper init() {
        if (stools == null) {
            stools = new ApplicationContextHelper();
        }
        return stools;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public Object getBean(String beanName) {
        return context.getBean(beanName);
    }
    
    public static Object getBean(Class beanClass) {
        return context.getBean(beanClass);
    }
}
