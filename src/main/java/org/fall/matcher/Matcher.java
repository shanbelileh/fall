package org.fall.matcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:07:08 PM
 * @version : 1.0
 */
public interface Matcher<T>
{
    boolean matches(T t);

    Matcher<T> and(Matcher<? super T> other);

    Matcher<T> or(Matcher<? super T> other);
}
