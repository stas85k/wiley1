/**
 * Created by stas on 04/07/2017.
 */
package com.wiley;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;


public class WiTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://dhclinicappv2stg.item-soft.co.il/Login.aspx";
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    @Test
    public void testWiley() throws Exception {
//1
        driver.get("http://eu.wiley.com/WileyCDA/Section/index.html");
        assertEquals(driver.getCurrentUrl(), "http://eu.wiley.com/WileyCDA/Section/index.html");
        assertEquals(driver.getTitle(), "Wiley: Journals, books, and online products and services");
        assertTrue(isElementPresent(By.linkText("Home")));
        assertTrue(isElementPresent(By.linkText("Subjects")));
        assertTrue(isElementPresent(By.linkText("About Wiley")));
        assertTrue(isElementPresent(By.linkText("Contact Us")));
        assertTrue(isElementPresent(By.linkText("Help")));
        Log.info("1. passed: the links displayed in top navigation menu");
//2
        assertTrue(isElementPresent(By.linkText("Students")));
        assertTrue(isElementPresent(By.linkText("Authors")));
        assertTrue(isElementPresent(By.linkText("Instructors")));
        assertTrue(isElementPresent(By.linkText("Librarians")));
        assertTrue(isElementPresent(By.linkText("Societies")));
        assertTrue(isElementPresent(By.linkText("Conferences")));
        assertTrue(isElementPresent(By.linkText("Booksellers")));
        assertTrue(isElementPresent(By.linkText("Corporations")));
        assertTrue(isElementPresent(By.linkText("Institutions")));
        Log.info("2. passed: the 9 items under Resources sub-header are displayed");
//3
        driver.findElement(By.linkText("Students")).click();
        assertEquals(driver.getCurrentUrl(), "http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        assertTrue(isElementPresent(By.xpath("//h1[text() = 'Students']")));
        //assertTrue(isElementPresent(By.id("page-title")));
        Log.info("3. passed: the url is opened “Students” header is displayed");
//4
        assertTrue(isElementPresent(By.linkText("Resources For")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[1]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[2]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[3]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[4]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[5]/span")));
        //System.out.println("4 "+driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[4]/a")).getText());
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[6]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[7]/a")));
        //assertTrue(isElementPresent(By.linkText("Government Employees")));
        Log.info("4. passed: the items in the “Resources For“ menu are displayed");
//5
        /*Check the “Students” ant "Instructors" items have different style(color)*/
        assertNotEquals(driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[4]/a")).getCssValue("color"),
                driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[5]/span")).getCssValue("color"));
        System.out.println("4- " +
                driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[4]/a")).getCssValue("color"));
        System.out.println("5- " +
                driver.findElement(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")).getCssValue("color"));
        /*Check the clickable “Students” items is not present*/
        assertFalse(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[5]/a")));
        Log.info("5. passed: “Students” item is selected");
//6
        driver.findElement(By.linkText("Home")).click();
        Log.info("6. passed: “Home” link is clicked");
//7
        driver.findElement(By.id("id31")).click();
        assertTrue(isAlertPresent());
        assertEquals(closeAlertAndGetItsText(), "Please enter email address");
        Log.info("7. passed: the alert “Please enter email address” is appeared");
//8
        driver.findElement(By.id("EmailAddress")).sendKeys("stas85kgmail.com");
        driver.findElement(By.id("id31")).click();
        assertTrue(isAlertPresent());
        assertEquals(closeAlertAndGetItsText(), "Invalid email address.");
        Log.info("8. passed: the alert “Invalid email address");

//9
        driver.findElement(By.id("query")).sendKeys("for dummies");
        driver.findElement(By.xpath("//input[@value='submit']")).click();
        Thread.sleep(2000);
        assertEquals(driver.getTitle(), "Wiley: Search Results");
        assertTrue(isElementPresent(By.id("search-results")));
        Log.info("9. passed: search input is checked, list of items appeared");
//10
        String bookNm = "Global Logistics For Dummies";
        driver.findElement(By.linkText(bookNm)).click();
        assertEquals(bookNm, driver.findElement(By.className("productDetail-title")).getText());
        Log.info("10. passed: the header is equal to the clicked book title");
//11
        driver.findElement(By.linkText("Home")).click();
        Log.info("11. passed: “Home” link at the top navigation menu is clicked");
//12
        driver.findElement(By.linkText("Institutions")).click();
        Thread.sleep(2000);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(4000);
        assertEquals(driver.getTitle(), "Higher Ed Services and Solutions | Wiley Education Services");
        Log.info("12. passed: the page is opened in new tab");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
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

