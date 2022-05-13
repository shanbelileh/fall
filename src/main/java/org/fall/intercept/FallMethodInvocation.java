package org.fall.intercept;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.AccessibleObject;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 5:14:20 PM
 * @version : 1.0
 */
public abstract class FallMethodInvocation implements MethodInvocation
{
    final Method method;
    final Object[] arguments;
    final Object proxy;

    protected FallMethodInvocation(Method method, Object[] arguments, Object proxy)
    {
        this.method = method;
        this.arguments = arguments;
        this.proxy = proxy;
    }

    @Override
    public Method getMethod()
    {
        return method;
    }

    @Override
    public Object[] getArguments()
    {
        return arguments;
    }

    @Override
   abstract public Object proceed() throws Throwable;

    @Override
    public Object getThis()
    {
        return proxy;
    }

    @Override
    public AccessibleObject getStaticPart()
    {
        return getMethod();
    }
}
