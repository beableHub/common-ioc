package org.beable.common.ioc.stereotype;


import java.lang.annotation.*;

/**
 * @author qing.wu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
