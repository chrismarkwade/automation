package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * @author Chris Wade
 */

public class SuiteListener implements ISuiteListener {
    
    private static final Logger log = LogManager.getLogger();
    
    @Override
    public void onStart(ISuite suite) {
        log.info("Test suite started: " + suite.getName());
    }
    
    @Override
    public void onFinish(ISuite suite) {
        log.info("Test suite finished: " + suite.getName());
    }
    
}
