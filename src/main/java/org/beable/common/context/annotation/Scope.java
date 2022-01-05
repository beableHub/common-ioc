package org.beable.common.context.annotation;

import java.lang.annotation.*;

/**
 * @author qing.wu
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
