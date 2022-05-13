package org.fall.intercept;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 5:12:22 PM
 * @version : 1.0
 */
public abstract class BeforeAdvise extends InterceptorWrapper
{
    @Override
    public abstract void before(MethodInvocation invocation) throws Throwable;
    
}
