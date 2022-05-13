package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static java.util.regex.Pattern.*;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 2:16:55 PM
 * @version : 1.0
 */
public class NamedMatcher extends AbstractMatcher<Object>
{
    String regex;
    Pattern pattern;

    protected NamedMatcher(String regex)
    {
        this.regex = regex;
        pattern = compile(regex);
    }

    @Override
    public boolean matches(Object o)
    {
        if (o instanceof Class)
        {
            return match(((Class) o).getSimpleName());
        }
        else if (o instanceof Method)
        {
            return match(((Method) o).getName());
        }
        return false;
    }

    private boolean match(String name)
    {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    @Override
    public String toString()
    {
        return "name(" + regex + ")";
    }
}
