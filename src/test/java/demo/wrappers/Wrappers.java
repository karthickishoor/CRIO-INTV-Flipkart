package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.Enter;

import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void flipkartFirstsearch(WebElement e, String product){        
        e.click();
        e.clear();
        e.sendKeys(product);
        e.sendKeys(Keys.ENTER);
    }

    public static void flipkartSecondsearch(WebElement e, String product){        
        e.click();
        e.clear();
        e.sendKeys(product);
        e.sendKeys(Keys.ENTER);
    }

    public static void flipkartsort(WebElement e){
        e.click();
    }

}
