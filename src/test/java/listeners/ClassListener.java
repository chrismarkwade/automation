package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IClassListener;
import org.testng.ITestClass;

/**
 * @author Chris Wade
 */

public class ClassListener implements IClassListener {
    
    private static final Logger log = LogManager.getLogger();
    
    @Override
    public void onBeforeClass(ITestClass testClass) {
        log.info("Test class started: " + testClass.getName().substring(6));
    }
    
    @Override
    public void onAfterClass(ITestClass testClass) {
        log.info("Test class finished: " + testClass.getName().substring(6));
    }
    
}
