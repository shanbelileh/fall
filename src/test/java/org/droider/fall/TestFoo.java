package org.droider.fall;

import org.fall.Fall;
import org.fall.proxy.Proxy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author : mohammad
 * @since : 5/13/2022 10:22 AM , Fri
 * fall
 **/

public class TestFoo
{
    @Test
   public void doTestFoo() throws Exception
    {

        Proxy proxy = Fall.createProxy(new TransactionModule());
        FooRepository fooRepository = proxy.newInstance(FooRepository.class);
        assertEquals(2, fooRepository.updateFoo("me"));

    }
}
