package org.droider.fall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : mohammad
 * @since : 5/13/2022 10:37 AM , Fri
 * fall
 **/
public class BarDao
{
    private final static Logger logger = LoggerFactory.getLogger(BarDao.class);

    @Transactional
    public int createBar(String name)
    {
        logger.debug("create bar {}", name);
        return 1;
    }
}
