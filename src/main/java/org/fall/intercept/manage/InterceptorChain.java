package org.fall.intercept.manage;

import org.aopalliance.intercept.MethodInterceptor;
import org.fall.proxy.MethodProxy;
import org.fall.intercept.FallMethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 23, 2009
 *         Time: 3:31:14 PM
 * @version : 1.0
 */
public class InterceptorChain
{
    final MethodInterceptor[] interceptors;
    final Method method;

    private MethodProxy proxy;

    public InterceptorChain(List<MethodInterceptor> interceptors, Method method)
    {
        this.method = method;
        this.interceptors = interceptors.toArray(new MethodInterceptor[0]);
    }

    public Method getMethod()
    {
        return method;
    }

    public void setProxy(MethodProxy proxy)
    {
        this.proxy = proxy;
        proxy.setOriginal(method);
    }

    public Object intercept(Object proxy, Object... args) throws Throwable
    {
//        try
//        {
            return new Invocation(method,args, proxy).proceed();
//        }
//        catch (Throwable throwable)
//        {
//            throwable.printStackTrace();
//            throw new RuntimeException(throwable);
//        }
    }


    class Invocation extends FallMethodInvocation
    {
        private int index = -1;

        protected Invocation(Method method, Object[] arguments, Object proxy)
        {
            super(method, arguments, proxy);
        }

        @Override
        public Object proceed() throws Throwable
        {
            try
            {
                index++;
                return index == interceptors.length
                        ? proxy.invokeSuper(getThis(), getArguments())
                        : interceptors[index].invoke(this);
            }
            finally
            {
                index--;
            }
        }
    }
}
