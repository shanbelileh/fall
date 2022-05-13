package org.droider.fall;

import org.fall.matcher.Matcher;
import org.fall.matcher.matchers.Matchers;
import org.fall.proxy.ProxyModule;

/**
 * @author : mohammad
 * @since : 5/13/2022 9:57 AM , Fri
 * fall
 **/
public class TransactionModule extends ProxyModule
{
    @Override
    public void config()
    {
        TransactionInterceptor interceptor = new TransactionInterceptor();

        bind(Matchers.annotatedWith(Transactional.class)).of(Matchers.any()).to(interceptor);

        bind(Matchers.any()).of(Matchers.subclassesOf(Repository.class)).to(interceptor);

    }
}
