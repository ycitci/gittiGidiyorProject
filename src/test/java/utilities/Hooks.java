package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Hooks {
    static WebDriverWait wait;
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    public  void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void click(WebElement e){
        wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(e)).click();
    }

    public void jsClick (WebElement e){
       try {
           js.executeScript("arguments[0].click();",e);
       }catch (NoSuchElementException w){
           w.printStackTrace();
       }
    }

    public  void writer (String producktInfo,String priceInfo){

        try{
            String path ="produckt.txt";
            FileWriter fileWriter = new FileWriter(path, true);

            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.append("ürün bilgileri :"+producktInfo+"\n");
            writer.append("Fiyati bilgsii :"+priceInfo+"\n");


            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int randomCount (int size){
        Random rnd = new Random();
        int random = rnd.nextInt(size);

        return random;
    }

    }
