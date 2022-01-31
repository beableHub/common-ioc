package org.beable.springframework.beans.factory.annotation;

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
