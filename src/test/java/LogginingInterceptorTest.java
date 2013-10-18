import com.nikom.testing.LogginingInterceptor;
import com.nikom.testing.Something;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;
import static org.powermock.api.mockito.PowerMockito.*;

import javax.interceptor.InvocationContext;

/**
 * User: nikom
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        LogginingInterceptor.class,
        Something.class,
        InvocationContext.class,
        Logger.class
})
public class LogginingInterceptorTest {

    private LogginingInterceptor interceptor;
    private InvocationContext context = mock(InvocationContext.class);
    private static Logger logger;
    private Something something = mock(Something.class);
    private Long somethingId = 10L;

    @BeforeClass
    public static void before() {
        mockStatic(Logger.class);
        logger = mock(Logger.class);
        when(Logger.getLogger(anyString())).thenReturn(logger);
    }

    @Before
    public void setUp() {
        when(something.getId()).thenReturn(somethingId);
        Object[] somethingArray = {something};
        when(context.getParameters()).thenReturn(somethingArray);

        interceptor = new LogginingInterceptor();
    }

    @After
    public void cleanUp() {
        reset(logger);
    }

    @Test
    public void shouldWorkForTrue() throws Exception {
        when(context.getTarget()).thenReturn(true);

        interceptor.invoke(context);

        verify(logger, never()).info(anyString());
        verify(logger, only()).error(anyString());
    }

    @Test
    public void shouldWorkForFalse() throws Exception {
        when(context.getTarget()).thenReturn(false);

        interceptor.invoke(context);

        verify(logger, never()).info(anyString());
        verify(logger, never()).error(anyString());
    }
}
