package org.beable.common.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author killua
 */
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String value();
}
