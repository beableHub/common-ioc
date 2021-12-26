package org.beable.common.context;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.Aware;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/26
 */
public interface ApplicationContextAware extends Aware {

    void setApplicaitonContext(ApplicationContext applicaitonContext) throws BeansException;
}
