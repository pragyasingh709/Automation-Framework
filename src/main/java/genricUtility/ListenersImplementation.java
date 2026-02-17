package genricUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import genricUtility.BaseClass;
import genricUtility.JavaUtility;
import genricUtility.WebDriverUtility;

public class ListenersImplementation implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
	   String methodname = result.getMethod().getMethodName();
	   System.out.println(methodname+"----Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		   System.out.println(methodname+"----Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		   System.out.println(methodname+"----Failed");
		   
		   JavaUtility jutil= new JavaUtility();
		   WebDriverUtility wutil = new WebDriverUtility();
		   
		  String screentshotname = methodname+" "+jutil.toGenerateSystemDataAndTimeFormate();
		  
		  
		  try {
			wutil.toTakeScreenShot(BaseClass.sdriver, screentshotname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		   System.out.println(methodname+"----Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		   System.out.println("-----Suit Execution Started----");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("-----Suit Execution Finished---");
	}
	

}
