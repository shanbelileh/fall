package org.fall.core.policy;


import java.util.function.Predicate;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Nov 6, 2009
 *         Time: 8:40:13 PM
 * @version : 1.0
 */
public class ClassNamingPolicy implements NamingPolicy
{
    @Override
    public String getName(String source, Predicate<String> names)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(source);
        buffer.append("$ByFALL");
        String base = buffer.toString();
        String attempt = base;
        int index = 2;
        while (names.test(attempt))
        {
            attempt = base + "_" + index++;
        }
        return attempt;
    }
}
