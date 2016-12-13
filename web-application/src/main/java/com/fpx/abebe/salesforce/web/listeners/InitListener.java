package com.fpx.abebe.salesforce.web.listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ognl.OgnlRuntime;


public class InitListener implements ServletContextListener {

    public InitListener() 
    {
    }

    public void contextInitialized(ServletContextEvent sce) {
        OgnlRuntime.setSecurityManager(null);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}
