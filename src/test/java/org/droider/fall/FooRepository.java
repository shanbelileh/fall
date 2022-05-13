package org.droider.fall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : mohammad
 * @since : 5/13/2022 10:20 AM , Fri
 * fall
 **/
public class FooRepository implements Repository
{
    private final static Logger logger = LoggerFactory.getLogger(FooRepository.class);

    public int updateFoo(String name)
    {
        logger.debug("updating foo {}", name);
        return 1;
    }
}
