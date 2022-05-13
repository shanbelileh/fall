package org.fall.core.policy;


import java.util.function.Predicate;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Nov 6, 2009
 *         Time: 8:38:31 PM
 * @version : 1.0
 */
public interface NamingPolicy
{
    String getName(String source, Predicate<String> names);
}
