package org.fall.proxy.aspect;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 1:05:03 PM
 * @version : 1.0
 */
public interface MethodAspect
{
    public boolean matches(Class<?> clazz);

    public boolean matches(Method method);

     public List<MethodInterceptor> getInterceptors();
}
