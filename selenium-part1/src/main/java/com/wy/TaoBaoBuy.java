package com.wy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Classname TaoBaoBuy
 * @Description TaoBaoBuy
 * @Date 2020/12/16 17:08
 * @Created wangyong
 */
public class TaoBaoBuy {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\work\\account\\selenium-study\\selenium-part1\\src\\main\\resources\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        System.out.println(webDriver.getPageSource());
        System.out.println(webDriver.getWindowHandle());
        System.out.println(webDriver.getWindowHandles());
        //跳到这url
        webDriver.get("https://www.taobao.com");
        while (true){
            //
            List<WebElement> elements = webDriver.findElements(By.linkText("亲，请登录"));
            if(!elements.isEmpty()){
                //休眠五分钟去登陆
                Thread.sleep(60 * 5 * 1000);
            }else{
                System.out.println("登录成功 退出登录");
                break;
            }
        }
        //获取当前时间戳
        long epochSecond = Instant.now().getEpochSecond();
        //获取当前时间
        long l = LocalDate.now().atStartOfDay().plusHours(20).atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(l);
        LocalDateTime.now().atZone(ZoneId.systemDefault());
        //刷新购物车界面
        webDriver.get("https://cart.taobao.com/cart.htm");
        while (true){
            //进行刷新
            if(l > epochSecond - 1){
                //睡眠一毫秒
                Thread.sleep(1);
                System.out.println("当前时间还没有到");
                epochSecond = Instant.now().getEpochSecond();
            }else{
                //跳出循环 进行添加购物车 点击结算，选择全选按钮
                break;
            }
        }
        //
        webDriver.findElement(By.id("J_SelectAll1"))
                .click();
        while (true){
            //点击结算按钮
            WebElement jGo = null;
            try {
                 jGo = webDriver.findElement(By.id("J_Go"));
            }catch (Exception e){
                //页面都没有了进行跳转到购物车
                webDriver.get("https://cart.taobao.com/cart.htm");
                webDriver.findElement(By.id("J_SelectAll1"))
                        .click();
            }
            try {
                jGo.click();
            }catch (Exception e){
                //
                System.out.println("时间还没有到");
            }
            List<WebElement> es = webDriver.findElements(By.linkText("提交订单"));
            if(!es.isEmpty()){
                for (WebElement e:es) {
                    try {
                        e.click();
                        System.out.println("已经提交订单了");
                        break;
                    }catch (Exception e1){

                    }
                }
            }
        }
    }

}
