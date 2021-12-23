package org.beable.common.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author qing.wu
 */
public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URI_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URI_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
