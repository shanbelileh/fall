package org.fall.core;


import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.HashSet;
import java.util.function.Predicate;

import org.fall.util.TypeUtils;
import org.objectweb.asm.Type;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 7:26:05 PM
 * @version : 1.0
 */
public class Predicates
{
    public static Predicate rejectModifierPredicate(int rejectMask)
    {
        return new RejectModifierPredicate(rejectMask);
    }

    public static Predicate visibilityPredicate(Class source, boolean protectedOk)
    {
        return new VisibilityPredicate(source, protectedOk);
    }

    public static Predicate duplicatesPredicate()
    {
        return new DuplicatesPredicate();
    }

    static class RejectModifierPredicate implements Predicate<Member>
    {
        private int rejectMask;

        public RejectModifierPredicate(int rejectMask)
        {
            this.rejectMask = rejectMask;
        }

        public boolean evaluate(Member arg)
        {
            return ((arg).getModifiers() & rejectMask) == 0;
        }

        @Override
        public boolean test(Member member)
        {
            return evaluate(member);
        }
    }

    static class VisibilityPredicate implements Predicate
    {
        private boolean protectedOk;
        private String pkg;

        public VisibilityPredicate(Class source, boolean protectedOk)
        {
            this.protectedOk = protectedOk;
            pkg = TypeUtils.getPackageName(Type.getType(source));
        }

        public boolean evaluate(Object arg)
        {
            int mod = (arg instanceof Member) ? ((Member) arg).getModifiers() : (Integer) arg;
            if (Modifier.isPrivate(mod))
            {
                return false;
            }
            else if (Modifier.isPublic(mod))
            {
                return true;
            }
            else if (Modifier.isProtected(mod))
            {
                return protectedOk;
            }
            else
            {
                return pkg.equals(TypeUtils.getPackageName(Type.getType(((Member) arg).getDeclaringClass())));
            }
        }

        @Override
        public boolean test(Object o)
        {
            return evaluate(o);
        }
    }

    static class DuplicatesPredicate implements Predicate<Method>
    {
        private final Set<Method> unique = new HashSet<Method>();

        public boolean evaluate(Method arg)
        {
            return unique.add(((Method) arg));
        }

        @Override
        public boolean test(Method method)
        {
            return evaluate(method);
        }
    }


}
