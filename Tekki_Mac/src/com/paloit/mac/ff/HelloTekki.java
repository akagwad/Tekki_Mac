package com.paloit.mac.ff;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HelloTekki {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private final static Logger LOGGER = Logger.getLogger(HelloTekki.class.getName());

  @Before
  public void setUp() throws Exception {
	System.out.println("Test Run Started! Mac-Firefox");
	Handler fileHandler  = new FileHandler("./src/com/paloit/mac/ff/tekki_Mac_Firefox.log");
	LOGGER.addHandler(fileHandler);
	//Setting levels to handlers and LOGGER
	fileHandler.setLevel(Level.ALL);
	LOGGER.setLevel(Level.ALL);

	System.setProperty("webdriver.gecko.driver", "./Mac_Dependencies/geckodriver");
    driver = new FirefoxDriver();
    baseUrl = "http://192.168.0.244:8000/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHelloTekki() throws Exception {
    driver.get(baseUrl + "/auth/login?next=%2F");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("achal");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("achal");
    driver.findElement(By.name("user_login")).click();
  }

  @After
  public void tearDown() throws Exception {
//    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
      LOGGER.log(Level.INFO,"Tekki Login Feature Failed!: Test Failed");
    }
    else{
    	LOGGER.log(Level.INFO,"Tekki Login Feature is succesful!: Test Pass");
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
