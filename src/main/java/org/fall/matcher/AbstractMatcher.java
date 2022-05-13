package org.fall.matcher;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 1:08:38 PM
 * @version : 1.0
 */
public abstract class AbstractMatcher<T> implements Matcher<T>
{

    public Matcher<T> and(final Matcher<? super T> other)
    {
        return new AndMatcher<T>(this, other);
    }

    public Matcher<T> or(Matcher<? super T> other)
    {
        return new OrMatcher<T>(this, other);
    }

    static class AndMatcher<T> extends AbstractMatcher<T>
    {
        final Matcher<? super T> _this;
        final Matcher<? super T> other;

        public AndMatcher(Matcher<? super T> _this, Matcher<? super T> other)
        {
            this._this = _this;
            this.other = other;
        }

        public boolean matches(T t)
        {
            return _this.matches(t) && other.matches(t);
        }
    }

    static class OrMatcher<T> extends AbstractMatcher<T>
    {
        final Matcher<? super T> _this;
        final Matcher<? super T> other;

        public OrMatcher(Matcher<? super T> _this, Matcher<? super T> other)
        {
            this._this = _this;
            this.other = other;
        }

        public boolean matches(T t)
        {
            return _this.matches(t) || other.matches(t);
        }
    }
}
