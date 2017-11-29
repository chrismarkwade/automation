package listeners;

import static utils.TakeScreenshot.takeScreenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author Chris Wade
 */

public class TestListener implements ITestListener {
    
    private static final Logger log = LogManager.getLogger();
    
    @Override
    public void onStart(ITestContext context) {
        log.info("Suite test started: " + context.getName());
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test method started: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test method passed: " + result.getName());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test method skipped: " + result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test method failed: " + result.getName());
        takeScreenshot(result.getName());
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("Test method failed but still within success percentage: " + result.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        log.info("Suite test finished: " + context.getName());
    }
    
}
