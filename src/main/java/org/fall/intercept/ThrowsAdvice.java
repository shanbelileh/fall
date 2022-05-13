package org.fall.intercept;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 15, 2009
 *         Time: 12:05:56 PM
 * @version : 1.0
 */
public abstract class ThrowsAdvice extends InterceptorWrapper
{
    @Override
    public abstract Object handleException(Throwable throwable) throws Throwable;
}
