package org.fall.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.fall.matcher.Matcher;
import static org.fall.matcher.matchers.Matchers.*;
import org.fall.proxy.aspect.MethodAspect;

import java.lang.reflect.Method;
import java.util.List;
import static java.util.Arrays.*;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 5:58:59 PM
 * @version : 1.0
 */
public class MethodAspectBinder implements MethodBinder, MethodAspect
{
    Matcher<? super Class<?>> classMatcher;
    Matcher<? super Method> methodMatcher;
    List<MethodInterceptor> interceptors;

    protected MethodAspectBinder(Matcher<? super Method> methodMatcher)
    {
        setMethodMatcher(methodMatcher);
        setClassMatcher(any());
    }

    public MethodAspectBinder of(Matcher<? super Class<?>> classMatcher)
    {
        setClassMatcher(classMatcher);
        return this;
    }

    public void to(MethodInterceptor... interceptors)
    {
        setInterceptors(asList(interceptors));
    }

    private void setClassMatcher(Matcher<? super Class<?>> classMatcher)
    {
        this.classMatcher = classMatcher;
    }

    private void setMethodMatcher(Matcher<? super Method> methodMatcher)
    {
        this.methodMatcher = methodMatcher;
    }

    private void setInterceptors(List<MethodInterceptor> interceptors)
    {
        this.interceptors = interceptors;
    }

    public List<MethodInterceptor> getInterceptors()
    {
        return interceptors;
    }

    public boolean matches(Class<?> clazz)
    {
        return classMatcher.matches(clazz);
    }

    public boolean matches(Method method)
    {
        return methodMatcher.matches(method);
    }
}
