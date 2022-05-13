package org.droider.fall;

import java.lang.annotation.*;

/**
 * @author : mohammad
 * @since : 5/13/2022 9:54 AM , Fri
 * fall
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Transactional
{
}
