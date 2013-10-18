package com.nikom.testing;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * User: nikom
 */
public class LogginingInterceptor {

    private static Logger logger = Logger.getLogger(LogginingInterceptor.class);

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        Something something = (Something) ctx.getParameters()[0];
        boolean result = Boolean.parseBoolean(String.valueOf(ctx.getTarget()));

        if (result) {
            logger.info("loguje ! " + something.getId());
        }

        return result;
    }

}
