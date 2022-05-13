package org.fall.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 5:07:06 PM
 * @version : 1.0
 */
abstract class InterceptorWrapper implements MethodInterceptor
{
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        Object result;
        before(invocation);
        try
        {
            result = invocation.proceed();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            return handleException(t);
        }
        return after(result, invocation);
    }


    public void before(MethodInvocation invocation) throws Throwable
    {
        // do nothing
    }

    public Object after(Object result, MethodInvocation invocation) throws Throwable
    {
        return result;
    }

    public Object handleException(Throwable throwable) throws Throwable
    {
        throw throwable;
    }
}
