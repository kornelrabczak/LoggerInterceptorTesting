package com.nikom.testing;

import javax.interceptor.Interceptors;

/**
 * User: nikom
 */
public class SomeRandomClazz {

    @Interceptors(LogginingInterceptor.class)
    public boolean someRandomBooleanMethod(Something something) {
        return true;
    }

}
