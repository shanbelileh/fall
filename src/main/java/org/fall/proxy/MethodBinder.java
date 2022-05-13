package org.fall.proxy;

import org.fall.matcher.Matcher;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;

import static java.util.Arrays.asList;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 30, 2009
 *         Time: 9:50:44 AM
 * @version : 1.0
 */
public interface MethodBinder
{
   default public MethodBinder of(Matcher<? super Class<?>> classMatcher){return this;};


    public void to(MethodInterceptor... interceptors);
}
