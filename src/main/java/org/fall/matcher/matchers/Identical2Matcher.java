package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 30, 2009
 *         Time: 9:46:13 AM
 * @version : 1.0
 */
public class Identical2Matcher extends AbstractMatcher<Object>
{
    Object o;

    public Identical2Matcher(Object o)
    {
        this.o = o;
    }

    @Override
    public boolean matches(Object other)
    {
        return o == other;
    }

    public String toString()
    {
        return "identicalTo(" + o + ")";
    }
}
