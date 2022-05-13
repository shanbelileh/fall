package org.fall.util;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 5:17:59 PM
 * @version : 1.0
 */
public class FallClassLoader extends ClassLoader
{
    ClassLoader parent;

    public FallClassLoader(ClassLoader parent)
    {
        super(parent);
        this.parent = parent;
    }

    public FallClassLoader()
    {
    }

    public Class defineClass(String name, byte[] b)
    {
        return defineClass(name, b, 0, b.length);
    }
}
