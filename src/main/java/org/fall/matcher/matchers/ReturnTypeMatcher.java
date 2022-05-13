package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;
import org.fall.matcher.Matcher;

import java.lang.reflect.Method;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:43:19 PM
 * @version : 1.0
 */
public class ReturnTypeMatcher extends AbstractMatcher<Method>
{
    Matcher<? super Class<?>> returnType;

    protected ReturnTypeMatcher(Matcher<? super Class<?>> returnType)
    {
        this.returnType = returnType;
    }

    @Override
    public boolean matches(Method m)
    {
        return returnType.matches(m.getReturnType());
    }

    @Override
    public String toString()
    {
        return "returns(" + returnType + ")";
    }
}
