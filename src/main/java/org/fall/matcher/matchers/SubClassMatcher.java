package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:32:40 PM
 * @version : 1.0
 */
public class SubClassMatcher extends AbstractMatcher<Class<?>>
{
    Class<?> superclass;

    protected SubClassMatcher(Class<?> superclass)
    {
        this.superclass = superclass;
    }

    @Override
    public boolean matches(Class subclass)
    {
        return superclass.isAssignableFrom(subclass);
    }

    @Override
    public String toString()
    {
        return "subclassesOf(" + superclass.getSimpleName() + ".class)";
    }
}
