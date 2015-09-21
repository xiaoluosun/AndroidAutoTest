package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.RegPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.allinmd.util.RandomStr;
import com.runtime.listener.Assertion;

/**
 * 邮箱注册-跳过认证
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class RegEmailCase {
	private static AndroidDriver driver;
	public static String username;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new RegPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 验证"立即注册"按钮是否存在以确定页面正常跳转
	 */
	@Test (priority = 1)
	public void assertRegButton() {
		Assertion.assertEquals(true, RegPageElements.regBtnIsExist(), "\"立即注册\"按钮未找到，页面不符或元素改变，请检查！");
    }
	
	/**
	 * 注册邮箱账号
	 */
	@Test (priority = 2)
//	@Test (groups = {"email_reg"})
	@Parameters({"GLOBAL_PASSWORD"})
	public void regEmail(String GLOBAL_PASSWORD) {
		username = RandomStr.randomEmail();
		
		RegPageElements.regEmailFlow(username, GLOBAL_PASSWORD);
	}
	
}
