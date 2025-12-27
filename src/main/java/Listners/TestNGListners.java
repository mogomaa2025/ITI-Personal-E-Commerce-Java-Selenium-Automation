package Listners;

import assertions.Assertions;
import coreMedia.ScreenRecordManager;
import coreMedia.ScreenshotsManager;
import driverFactory.WebDriverFactory;
import listenerUtils.ListenersAssistant;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import readers.Log;
import readers.PropertyReader;
import reports.AllureAttachmentManager;
import reports.AllureGenerator;
import reports.AllureSetupEnvironment;

import static reports.AllureAttachmentManager.attachLogs;

public class TestNGListners implements ISuiteListener, IExecutionListener,IClassListener, IInvokedMethodListener, ITestListener {

	@Override
	public void onExecutionStart() {

		Log.info("Test Execution started");
		PropertyReader.loadProperties();

		ListenersAssistant.cleanTestOutputDirectories()
				.createTestOutputDirectories();

		AllureSetupEnvironment.setAllureEnvironment()
				.downloadAndExtract();

	}

	@Override
	public void onExecutionFinish() {
		AllureGenerator.copyHistory()
				.generateSingleReport()
				.generateFullReport()
				.openReport();
		Log.info("Test Execution Finished");

	}

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("╔═══════════════════════════════════════════════════════╗");
        Log.info("║ TEST STARTED: " + result.getMethod().getMethodName());
        Log.info("╚═══════════════════════════════════════════════════════╝");
    }

    public void onTestSuccess(ITestResult result) {

        Log.testPassed("Test passed: " + result.getMethod().getMethodName());
        Log.info("ok Test Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
    }

    public void onTestFailure(ITestResult result) {
        try {
            Log.info("═══════════════════════════════════════════════════════════════════════════════");
            Log.testFailed("Test failed: " + result.getMethod().getMethodName());
            Log.error("Failure Reason: " + result.getThrowable().getMessage());
            Log.info("Test Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
            Log.info("══════════════════════════════════════════════════════════════════════════════════");

        } catch (Exception e) {
            Log.warn("test failure failed to log: " + e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        Log.warn("Test skipped: " + result.getMethod().getMethodName());
        Log.warn("Reason: " + result.getSkipCausedBy());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.warn("Test failed but within success percentage: " + result.getMethod().getMethodName());
    }



	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// not implemented
		if (method.isTestMethod()) {

			ScreenRecordManager.startRecording();
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		WebDriver driver=null;
		if (method.isTestMethod()) {
			try {
				driver = WebDriverFactory.get();
				ScreenshotsManager.captureScreenshot(driver, testResult.getName());
				ScreenRecordManager.stopRecording(testResult.getName());
				AllureAttachmentManager.attachRecords(testResult.getName());
				attachLogs();

			} catch (Exception e) {
				Log.error("Error while capturing screenshot: " + e.getMessage());
			}
		}

		Assertions.assertAll(testResult);
	}



    @Override
    public void onStart(org.testng.ITestContext context) {
        Log.info("═══════════════════════════════════════════════════");
        Log.info("TEST SUITE STARTED: " + context.getName());
        Log.info("═══════════════════════════════════════════════════");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        Log.info("═══════════════════════════════════════════════════");
        Log.info("TEST SUITE FINISHED: " + context.getName());
        Log.info("Total Tests: " + context.getAllTestMethods().length);
        Log.info("Passed: " + context.getPassedTests().size());
        Log.info("Failed: " + context.getFailedTests().size());
        Log.info("Skipped: " + context.getSkippedTests().size());
        Log.info("═══════════════════════════════════════════════════");
    }




}
