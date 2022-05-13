package org.fall.util;


import static org.fall.core.Predicates.*;
import org.objectweb.asm.Opcodes;

import java.util.List;
import java.util.Set;


/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 7:21:18 PM
 * @version : 1.0
 */
public class FallReflectUtility
{
    public static void getMethods(Class superclass, Class[] interfaces, List methods, List interfaceMethods, Set forcePublic)
    {
        addAllMethods(superclass, methods);
        List target = (interfaceMethods != null) ? interfaceMethods : methods;
        if (interfaces != null)
        {
            for (Class anInterface : interfaces)
            {
//                if (interfaces[i] != Factory.class)
                {
                    addAllMethods(anInterface, target);
                }
            }
        }
//        if (interfaceMethods != null)
//        {
//            if (forcePublic != null)
//            {
//                forcePublic.addAll(MethodWrapper.createSet(interfaceMethods));
//            }
//            methods.addAll(interfaceMethods);
//        }
        CollectionUtils.filter(methods, rejectModifierPredicate(Opcodes.ACC_STATIC));
        CollectionUtils.filter(methods, visibilityPredicate(superclass, true));
        CollectionUtils.filter(methods, duplicatesPredicate());
        CollectionUtils.filter(methods, rejectModifierPredicate(Opcodes.ACC_FINAL));
    }

    public static List addAllMethods(final Class type, final List list)
    {


        list.addAll(java.util.Arrays.asList(type.getDeclaredMethods()));
        Class superclass = type.getSuperclass();
        if (superclass != null&& superclass!=Object.class)
        {
            addAllMethods(superclass, list);
        }
        Class[] interfaces = type.getInterfaces();
        for (int i = 0; i < interfaces.length; i++)
        {
            addAllMethods(interfaces[i], list);
        }

        return list;
    }

    public static Class[] getTypes(Object[] args)
    {
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++)
        {
            classes[i] = args[i].getClass();
        }
        return classes;
    }
}
