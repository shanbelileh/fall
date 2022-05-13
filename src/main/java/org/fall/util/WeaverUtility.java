package org.fall.util;

import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import java.lang.reflect.Constructor;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 30, 2009
 *         Time: 4:27:30 PM
 * @version : 1.0
 */
public class WeaverUtility
{
    public static String[] getInterfaces(Class source, Type additional)
    {
        String[] intfs = new String[source.getInterfaces().length + 1];
        intfs[0] = additional.getInternalName();

        for (int i = 1; i < intfs.length; i++)
        {
            intfs[i] = Type.getInternalName(source.getInterfaces()[i-1]);
        }
        return intfs;
    }

    public static String[] getExceptions(Constructor contructor)
    {
        if (contructor.getExceptionTypes().length == 0) return null;
        String[] excps = new String[contructor.getExceptionTypes().length];
        for (int i = 0; i < excps.length; i++)
        {
            excps[i] = Type.getInternalName(contructor.getExceptionTypes()[i]);
        }
        return excps;
    }

    public static String[] getExceptions(java.lang.reflect.Method method)
    {
        if (method.getExceptionTypes().length == 0) return null;
        String[] excps = new String[method.getExceptionTypes().length];
        for (int i = 0; i < excps.length; i++)
        {
            excps[i] = Type.getInternalName(method.getExceptionTypes()[i]);
        }
        return excps;
    }


    public static Method getMethod(java.lang.reflect.Method method)
    {
        Type returnType = Type.getType(method.getReturnType());
        Type[] argTypes = new Type[method.getParameterTypes().length];
        for (int i = 0; i < argTypes.length; ++i)
        {
            argTypes[i] = Type.getType(method.getParameterTypes()[i]);
        }
        return new Method(method.getName(), returnType, argTypes);
    }
}
