package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.LogoutPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 邮箱登录-退出登录
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class LoginEmailCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new LogoutPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 验证"唯医会员登录"按钮是否存在以确定页面正常跳转
	 */
	@Test (priority = 1)
	public void assertRegButton() {
		Assertion.assertEquals(true, LoginPageElements.allmdBtnIsExist(), "\"唯医会员登录\"按钮未找到，页面不符或元素改变，请检查！");
    }
	
	/**
	 * 邮箱登录唯医
	 */
	@Test (priority = 2)
	@Parameters({"LOGIN_EMAIL_USER", "GLOBAL_PASSWORD"})
	public void loginEmail(String LOGIN_EMAIL_USER, String GLOBAL_PASSWORD) {
		LoginPageElements.allinLogin(LOGIN_EMAIL_USER, GLOBAL_PASSWORD);
	}
	
	@Test (priority = 3)
	public void logoutEmail() {
		LogoutPageElements.allinLogout();
		Assertion.assertEquals(true, LoginPageElements.allmdBtnIsExist());
	}
}
