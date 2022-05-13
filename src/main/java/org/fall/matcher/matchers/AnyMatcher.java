package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:13:08 PM
 * @version : 1.0
 */
public class AnyMatcher extends AbstractMatcher<Object>
{

    protected AnyMatcher()
    {
    }

    @Override
    public boolean matches(Object o)
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "any()";
    }
}
