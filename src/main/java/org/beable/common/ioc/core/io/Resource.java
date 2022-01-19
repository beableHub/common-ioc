package org.beable.common.ioc.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author qing.wu
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
