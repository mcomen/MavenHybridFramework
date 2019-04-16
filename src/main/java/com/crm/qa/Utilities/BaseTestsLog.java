package com.crm.qa.Utilities;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseTestsLog implements ITestListener 
{

	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@BeforeSuite
	public void beforeSuite (ITestContext context)
	{
		String log4jConfigFile = "";
		log4jConfigFile = System.getProperty("user.dir") + File.separator + "/MavenHybridFramework/src/main/java/com/crm/qa/Utilities/Log4j.xml";
		System.setProperty("log4j.configurationFile", log4jConfigFile);
		LogManager.info("Running .." + context.getCurrentXmlTest().getSuite().getName());
	}

	@BeforeMethod
	public void beforeMethod (Method method)
	{
		LogManager.info("Entering the method -" + method.getName());
	}

	public void onStart(ITestContext iTestContext)
	{
		LogManager.Log.info("Started Test -" + iTestContext.getCurrentXmlTest().getName());
	}

	public void onFinish(ITestContext iTestContext)
	{
		LogManager.info("Finished Test " + iTestContext.getName());
	}

	public void onTestStart(ITestResult iTestResult) 
	{
		LogManager.startTestCase(getTestMethodName(iTestResult));
	}

	public void onTestSuccess(ITestResult iTestResult)
	{
		LogManager.endTestCase(getTestMethodName(iTestResult));
	}

	public void onTestFailure(ITestResult iTestResult)
	{
		LogManager.error(getTestMethodName(iTestResult) + " failed");
	}

	public void onTestSkipped(ITestResult iTestResult)
	{
		LogManager.warn(getTestMethodName(iTestResult) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult)
	{
		LogManager.info(getTestMethodName(iTestResult));
	}

	@AfterMethod
	public void afterMethod (ITestResult result)
	{
		LogManager.info("Exited the method" + result.getMethod().getMethodName());
	}

	@AfterSuite
	public void aftereSuite (ITestContext context)
	{
		LogManager.info("Finished running " + context.getCurrentXmlTest().getSuite().getName());
	}

}