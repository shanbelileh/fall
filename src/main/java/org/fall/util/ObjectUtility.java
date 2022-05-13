package org.fall.util;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 2:12:05 PM
 * @version : 1.0
 */
public class ObjectUtility
{
    public static <T> T checkNull(T t, String message)
    {
        if (t == null)
        {
            throw new NullPointerException(message);
        }
        return t;
    }
}
