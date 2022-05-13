package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:41:03 PM
 * @version : 1.0
 */
public class PackageMatcher extends AbstractMatcher<Class>
{
    Package _package;

    protected PackageMatcher(Package _package)
    {
        this._package = _package;
    }

    @Override
    public boolean matches(Class c)
    {
        return c.getPackage().equals(_package);
    }

    @Override
    public String toString()
    {
        return "package(" + _package.getName() + ")";
    }
}
