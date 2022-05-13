package org.fall.core.policy;


import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Nov 6, 2009
 *         Time: 8:42:13 PM
 * @version : 1.0
 */
public class NamesPredicate implements Predicate<String>
{
    HashSet<String> names = new HashSet<String>();

    public boolean evaluate(String name)
    {
        return names.contains(name);
    }

    public String add(String name)
    {
        names.add(name);
        return name;
    }

    @Override
    public boolean test(String s)
    {
        return evaluate(s);
    }
}
