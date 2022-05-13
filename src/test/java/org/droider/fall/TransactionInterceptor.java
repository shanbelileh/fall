package org.droider.fall;

import org.aopalliance.intercept.MethodInvocation;
import org.fall.intercept.AroundAdvise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author : mohammad
 * @since : 5/13/2022 10:04 AM , Fri
 * fall
 **/
public class TransactionInterceptor extends AroundAdvise
{
    private final static Logger logger = LoggerFactory.getLogger(TransactionInterceptor.class);

    @Override
    public void before(MethodInvocation invocation) throws Throwable
    {
        logger.debug("before transaction");
        logger.debug(invocation.getMethod().getName());
        logger.debug(Arrays.toString(invocation.getArguments()));
    }

    @Override
    public Object after(Object result, MethodInvocation invocation) throws Throwable
    {
        logger.debug("commit transaction : {}",result);
        logger.debug(invocation.getMethod().getName());
        logger.debug(Arrays.toString(invocation.getArguments()));
        return 2;
    }

    @Override
    public Object handleException(Throwable throwable) throws Throwable
    {
        return super.handleException(throwable);
    }
}
