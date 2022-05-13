package org.fall.proxy.enhance;

import org.fall.core.policy.NamesPredicate;
import org.fall.core.policy.ClassNamingPolicy;
import org.fall.core.policy.MethodNamingPolicy;

import java.util.function.Predicate;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Nov 13, 2009
 *         Time: 10:50:32 AM
 * @version : 1.0
 */
public abstract class AbstractWeaver implements Weaver
{
    final static Predicate<String> classPredicate = new NamesPredicate();
    final static Predicate<String> methodPredicate = new NamesPredicate();

    ClassNamingPolicy classNamingPolicy;

    MethodNamingPolicy methodNamingPolicy;



    public ClassNamingPolicy getClassNamingPolicy()
    {
        return classNamingPolicy;
    }

    public void setClassNamingPolicy(ClassNamingPolicy classNamingPolicy)
    {
        this.classNamingPolicy = classNamingPolicy;
    }

    public MethodNamingPolicy getMethodNamingPolicy()
    {
        return methodNamingPolicy;
    }

    public void setMethodNamingPolicy(MethodNamingPolicy methodNamingPolicy)
    {
        this.methodNamingPolicy = methodNamingPolicy;
    }
}
