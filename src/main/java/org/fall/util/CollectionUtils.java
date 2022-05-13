package org.fall.util;


import java.util.*;
import java.util.function.Predicate;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 7:34:49 PM
 * @version : 1.0
 */
public class CollectionUtils
{
    private CollectionUtils() { }



    public static void reverse(Map source, Map target)
    {
        for (Iterator it = source.keySet().iterator(); it.hasNext();)
        {
            Object key = it.next();
            target.put(source.get(key), key);
        }
    }

    public static Collection filter(Collection c, Predicate p)
    {
        Iterator it = c.iterator();
        while (it.hasNext())
        {
            if (!p.test(it.next()))
            {
                it.remove();
            }
        }
        return c;
    }



    public static Map getIndexMap(List list)
    {
        Map indexes = new HashMap();
        int index = 0;
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            indexes.put(it.next(), index++);
        }
        return indexes;
    }

    public static <T, S> List<T> cast(List<S> source)
    {
        List<T> newList = new ArrayList<T>();
        for (S s : source)
        {
            newList.add((T) s);
        }
        return newList;
    }
}

