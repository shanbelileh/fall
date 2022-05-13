package org.droider.fall;

import org.fall.Fall;
import org.fall.proxy.Proxy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author : mohammad
 * @since : 5/13/2022 10:39 AM , Fri
 * fall
 **/
public class TeatBar
{
    @Test
    public void doTestBar() throws Exception
    {

        Proxy proxy = Fall.createProxy(new TransactionModule());
        BarDao dao = proxy.newInstance(BarDao.class);
        assertEquals(2, dao.createBar("me"));

    }

}
