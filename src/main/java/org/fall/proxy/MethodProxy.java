package org.fall.proxy;

import org.fall.util.FallReflectUtility;
import static org.fall.util.FallReflectUtility.*;

import java.lang.reflect.Method;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 30, 2009
 *         Time: 2:00:15 PM
 * @version : 1.0
 */
public class MethodProxy
{
    Method method;
    String name;
    Method original;
    public MethodProxy(Method method)
    {
        this.method = method;
    }

    public MethodProxy(String name)
    {
        this.name = name;
    }

    public void setOriginal(Method original)
    {
        this.original = original;
    }

    public Object invokeSuper(Object proxy, Object... args) throws Throwable
    {
        if (method == null) loadMethod(proxy, args);
        return method.invoke(proxy, args);
    }

    private void loadMethod(Object proxy, Object[] args)
    {
        try
        {
            method = proxy.getClass().getMethod(name, original.getParameterTypes());
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
