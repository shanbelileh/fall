package org.fall.intercept.manage;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 5:16:25 PM
 * @version : 1.0
 */
public final class FallInterceptorManager
{
    HashMap<Method, InterceptorChain> cache = new HashMap<Method, InterceptorChain>();

    public InterceptorChain getInterceptors(Method method)
    {
        return cache.get(method);
    }

    public void register(Method method, InterceptorChain chain)
    {
        cache.put(method, chain);
    }
}
