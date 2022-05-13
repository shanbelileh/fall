package org.fall.matcher.matchers;

import org.fall.matcher.AbstractMatcher;

import java.lang.reflect.AnnotatedElement;
import java.lang.annotation.Annotation;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:25:39 PM
 * @version : 1.0
 */
public class AnnotationMatcher extends AbstractMatcher<AnnotatedElement>
{
    Annotation annotation;

    protected AnnotationMatcher(Annotation annotation)
    {
        this.annotation = annotation;
    }

    @Override
    public boolean matches(AnnotatedElement element)
    {
        Annotation fromElement
                = element.getAnnotation(annotation.annotationType());
        return fromElement != null && annotation.equals(fromElement);
    }

    @Override
    public String toString()
    {
        return "annotatedWith(" + annotation + ")";
    }
}
