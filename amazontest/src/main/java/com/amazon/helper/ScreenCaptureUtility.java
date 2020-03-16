package com.amazon.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenCaptureUtility {

	public static WebDriver driver;

	Boolean result = false;
	public static String ScreenshotFolderName = ".\\ScreenshotFolders\\";
	public static String LinuxSCFolder = "ScreenshotFolders" + File.separator;

	public static void setScreenshotAll(WebDriver webDriver, String testCase, String methodName) throws IOException {
		try {
			Thread.sleep(2000);
			
			//Create Screenshot folder if it doesn't exist
			File screenShotFolder = new File(ScreenshotFolderName);

			if (!screenShotFolder.exists()) {
				screenShotFolder.mkdirs();
			}
			if(screenShotFolder.exists())
			{
				screenShotFolder.delete();
				screenShotFolder.mkdirs();
			}
			Calendar calendar = new GregorianCalendar();
			// String ProjectPath = "Execution Screenshots";

			String filename = "";

			if(SystemUtils.IS_OS_LINUX) {
				filename = LinuxSCFolder + "Screenshots" + File.separator + testCase + File.separator + calendar.get(Calendar.HOUR)
						+ "-" + calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + "_" + methodName
						+ ".jpg";
			}
			else {
				filename = ScreenshotFolderName + "Screenshots\\" + testCase + "\\" + calendar.get(Calendar.HOUR)
						+ "-" + calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + "_" + methodName
						+ ".jpg";
			}
			File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			org.apache.commons.io.FileUtils.copyFile(screenshotFile, new File(filename));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
