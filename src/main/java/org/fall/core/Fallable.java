package org.fall.core;

import org.fall.intercept.manage.FallInterceptorManager;

import java.lang.reflect.Method;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 30, 2009
 *         Time: 1:33:49 PM
 * @version : 1.0
 */
public interface Fallable
{
    public void setInterceptorManager(FallInterceptorManager interceptorManager);

    public FallInterceptorManager getInterceptorManager();

    public Object go(Method method, Object... args);
}
