package org.fall.matcher.matchers;

import org.fall.matcher.Matcher;
import static org.fall.util.ObjectUtility.*;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 2:10:42 PM
 * @version : 1.0
 */
public class Matchers
{
    public static Matcher<Object> any()
    {
        return new AnyMatcher();
    }

    public static <T> Matcher<T> not(final Matcher<? super T> matcher)
    {
        checkNull(matcher, "matcher");
        return new NotMatcher<T>(matcher);
    }

    public static Matcher<AnnotatedElement> annotatedWith(final Class<? extends Annotation> annotationType)
    {
        checkNull(annotationType, "annotation type");
        return new AnnotationTypeMatcher(annotationType);
    }


    public static Matcher<AnnotatedElement> annotatedWith(Annotation annotation)
    {
        checkNull(annotation, "annotation");
        return new AnnotationMatcher(annotation);
    }

    public static Matcher<Class<?>> subclassesOf(Class<?> superclass)
    {
        checkNull(superclass, "superclass");
        return new SubClassMatcher(superclass);
    }

    public static Matcher<Class> inPackage(Package p)
    {
        checkNull(p, "package");
        return new PackageMatcher(p);
    }

    public static Matcher<Object> named(String regex)
    {
        checkNull(regex, "name");
        return new NamedMatcher(regex);
    }

    public static Matcher<Method> returns(Matcher<? super Class<?>> returnType)
    {
        checkNull(returnType, "return type matcher");
        return new ReturnTypeMatcher(returnType);
    }

    public static Matcher<Object> identical2(Object o)
    {
        checkNull(o, "o");
        return new Identical2Matcher(o);
    }
}
