package org.beable.common.ioc.core.io;

/**
 * @author qing.wu
 */
public interface ResourceLoader {

    String CLASSPATH_URI_PREFIX = "classpath:";

    Resource getResource(String location);
}
