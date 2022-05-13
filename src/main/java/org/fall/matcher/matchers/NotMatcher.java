package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;
import org.fall.matcher.Matcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:17:36 PM
 * @version : 1.0
 */
public class NotMatcher<T> extends AbstractMatcher<T>
{
    Matcher<? super T> matcher;

    protected NotMatcher(Matcher<? super T> matcher)
    {
        this.matcher = matcher;
    }
    @Override
    public boolean matches(T t)
    {
        return !matcher.matches(t);
    }
    @Override
    public String toString()
    {
        return "not(" + matcher + ")";
    }
}
