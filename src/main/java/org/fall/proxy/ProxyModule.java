package org.fall.proxy;

import org.fall.matcher.Matcher;
import static org.fall.util.ObjectUtility.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 5:32:34 PM
 * @version : 1.0
 */
public abstract class ProxyModule
{
    List<MethodBinder> bindings;

    protected ProxyModule()
    {
        bindings = new ArrayList<MethodBinder>();
    }

    public abstract void config();

    public MethodBinder bind(Matcher<? super Method> methodMatcher)
    {
        return createBinder(checkNull(methodMatcher, "methodMatcher"));
    }

    private MethodBinder createBinder(Matcher<? super Method> matcher)
    {
        MethodBinder binder = new MethodAspectBinder(matcher);
        bindings.add(binder);
        return binder;
    }





    public List<MethodBinder> getBindings()
    {
        return bindings;
    }
}
