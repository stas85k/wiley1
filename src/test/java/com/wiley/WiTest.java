/**
 * Created by stas on 04/07/2017.
 */
package com.wiley;

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


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://dhclinicappv2stg.item-soft.co.il/Login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void testWiley() throws Exception {
//1
        driver.get("http://www.wiley.com/WileyCDA/Section/index.html");
        assertEquals(driver.getCurrentUrl(), "http://eu.wiley.com/WileyCDA/Section/index.html");
        assertEquals(driver.getTitle(), "Wiley: Journals, books, and online products and services");
        assertTrue(isElementPresent(By.linkText("Home")));
        assertTrue(isElementPresent(By.linkText("Subjects")));
        assertTrue(isElementPresent(By.linkText("About Wiley")));
        assertTrue(isElementPresent(By.linkText("Contact Us")));
        assertTrue(isElementPresent(By.linkText("Help")));
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
//3
        driver.findElement(By.linkText("Students")).click();
        assertEquals(driver.getCurrentUrl(), "http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        assertTrue(isElementPresent(By.xpath("//h1[text() = 'Students']")));
//4
        assertTrue(isElementPresent(By.linkText("Resources For")));

        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[1]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[2]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[3]/a")));
        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[4]/a")));
        System.out.println(" - " +
                driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[4]/a")).getCssValue("color"));

        assertTrue(isElementPresent(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")));
        System.out.println(" - -" +
                driver.findElement(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")).getCssValue("color"));

        /*assertTrue(isElementPresent(By.linkText("Government Employees")));
        System.out.println(" - " +
                driver.findElement(By.linkText("Government Employees")).getText());*/

        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[6]/a")));
        System.out.println(" - " +
                driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[6]/a")).getText());

        assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[7]/a")));
        System.out.println(" - " +
                driver.findElement(By.xpath("//div[@id='sidebar']/div/ul/li/ul/li[7]/a")).getText());

//driver.findElement(By.xpath("//a[contains(text(),'Students')]")).click();
//assertEquals(driver.getTitle(), "Wiley: Students");

//5

        assertTrue(isElementPresent(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")));
        System.out.println(" - -" +
                driver.findElement(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")).getTagName());

        assertTrue(isElementPresent(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")));

        System.out.println(" - -" +
                driver.findElement(By.xpath(".//*[@id='sidebar']/div/ul/li/ul/li[5]/span")).getText());


//6
        driver.findElement(By.linkText("Home")).click();
//7
        driver.findElement(By.id("id31")).click();
        assertTrue(isAlertPresent());
        assertEquals(closeAlertAndGetItsText(), "Please enter email address");
//8
        driver.findElement(By.id("EmailAddress")).sendKeys("stas85kgmail.com");
        driver.findElement(By.id("id31")).click();
        assertTrue(isAlertPresent());
        assertEquals(closeAlertAndGetItsText(), "Invalid email address.");
//9
        driver.findElement(By.id("query")).sendKeys("for dummies");
        driver.findElement(By.xpath("//input[@value='submit']")).click();
        assertEquals(driver.getTitle(), "Wiley: Search Results");
        assertTrue(isElementPresent(By.id("search-results")));
//10
        driver.findElement(By.xpath("//div[3]/div/div/div/div/a/img")).click();
        Thread.sleep(2000);
        System.out.println("111111 "+ By.xpath("//div[3]/div[2]/a"));
//        assertEquals(driver.getTitle(), "Wiley: Nikon D3400 For Dummies - Julie Adair King");
        //11
        driver.findElement(By.linkText("Home")).click();

//12

        driver.findElement(By.xpath("//div[@id='homepage-links']/ul/li[9]/a")).click();

        Thread.sleep(1000);

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));
        Thread.sleep(4000);

        assertEquals(driver.getTitle(), "Higher Ed Services and Solutions | Wiley Education Services");

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

