package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

import java.lang.reflect.AnnotatedElement;
import java.lang.annotation.Annotation;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:21:15 PM
 * @version : 1.0
 */
public class AnnotationTypeMatcher extends AbstractMatcher<AnnotatedElement>
{
    Class<? extends Annotation> annotationType;

    protected AnnotationTypeMatcher(Class<? extends Annotation> annotationType)
    {
        this.annotationType = annotationType;
    }

    @Override
    public boolean matches(AnnotatedElement element)
    {
        Annotation annotation = element.getAnnotation(annotationType);
        return annotation != null;
    }

    @Override
    public String toString()
    {
        return "annotatedWith(" + annotationType.getSimpleName() + ".class)";
    }
}
