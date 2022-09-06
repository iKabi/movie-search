package me.demo.listener;

import com.codeborne.selenide.ex.UIAssertionError;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class UIAssertionFailureListener implements ITestListener {

	@Override
	public void onTestFailure(final ITestResult result) {
		if (result.getThrowable() instanceof UIAssertionError err) {
			log.error("Test failed due to {}", err.getLocalizedMessage());
			var screenshot = err.getScreenshot();
			if (!screenshot.isPresent()) {
				log.error("Unable to capture screenshot");
			}
		}
	}

}
