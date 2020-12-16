package com.wy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @Classname TestOne
 * @Description TestOne
 * @Date 2020/12/16 15:51
 * @Created wangyong
 */
public class TestOne {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\work\\account\\selenium-study\\selenium-part1\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20L);
        driver.navigate().to("http://rdamp.dmall.com/login");
        WebElement input = driver.findElement(By.id("userCode"));
        input.sendKeys("hello");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("hello");
        List<WebElement> elements2 = driver.findElements(By.tagName("button"));
        int count = 1;
        for (int i = 0; i < elements2.size(); i++) {
            try {
                while (true) {
                    count = count + 1;
                    WebElement webElement = elements2.get(i);
                    webElement.click();
                    if (count == 12) {
                        input.clear();
                        input.sendKeys("yong.wang3");
                        password.clear();
                        password.sendKeys("Wy4213811994!");
                    }
                    if (!driver.getCurrentUrl().contains("login")) {
                        System.out.println("登录成功");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(driver.getCurrentUrl());
    }
}
