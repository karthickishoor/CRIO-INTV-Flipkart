package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v123.domstorage.model.Item;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test
    public void testCase01() throws InterruptedException{
        driver.get("http://www.flipkart.com/");
        WebElement serachBxEl = driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));        
        Wrappers.flipkartFirstsearch(serachBxEl, "Washing Machine");
        WebElement sortByPopularityEl = driver.findElement(By.xpath("//div[text()='Popularity']"));
        Wrappers.flipkartsort(sortByPopularityEl);
        List<WebElement> listOfprdRatings = driver.findElements(By.xpath("//div[@class='XQDdHH']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int countOfrateingsbelow4=0;
        for(WebElement ratings : listOfprdRatings){
            js.executeScript("arguments[0].scrollIntoView(true);", ratings);
            double rateing = Double.parseDouble(ratings.getText());
            if(rateing<=4.0){
                countOfrateingsbelow4=countOfrateingsbelow4+1;
                
            }
        }        
        System.out.println("The number of count that ratings are less than or equal to 4 is : "+countOfrateingsbelow4);
        Thread.sleep(2000);
    }

    @Test
    public void testCase02() throws InterruptedException{
        WebElement serachBx2El = driver.findElement(By.xpath("//input[@class='zDPmFV']"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(serachBx2El));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", serachBx2El);        
        Wrappers.flipkartSecondsearch(serachBx2El, "iPhone");
        Thread.sleep(2000);
        List<WebElement> cardContainertEl = driver.findElements(By.xpath("//div[@class='tUxRFH']"));
        for(WebElement card : cardContainertEl){
            js.executeScript("arguments[0].scrollIntoView(true);",card);
            String tittle = card.findElement(By.xpath(".//div[@class='KzDlHZ']")).getText();
            //System.out.println(tittle);
            try {
                WebElement offerPercentEl = card.findElement(By.xpath(".//div[@class='UkUFwK']/span"));
                String offerPercent =offerPercentEl.getText();
                int offer=Integer.parseInt(offerPercent.replaceAll("[^0-9]", ""));
                //System.out.println(offer);
                if(offer > 17){                
                    System.out.println(tittle+" is on discount "+offerPercent);
                }
                
            } catch (Exception e) {
                // TODO: handle exception
                continue;
            }
            
            

            
                
                
            

            
        }
               
            
        
    }
    @Test
        public void testCase03() throws InterruptedException{
            Thread.sleep(1000);
            WebElement serachBx2El = driver.findElement(By.xpath("//input[@class='zDPmFV']"));
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(serachBx2El));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = '';", serachBx2El);        
            Wrappers.flipkartSecondsearch(serachBx2El, "Coffee Mug");
            Thread.sleep(1000);
            WebElement starEl = driver.findElement(By.xpath("(//div[@class='XqNaEv'])[1]"));
            starEl.click();
            Thread.sleep(1000); 
            //List<WebElement> reviewsEl = driver.findElements(By.xpath("//span[@class='Wphh3N']"));
            
            List<WebElement> cardContainerEl = driver.findElements(By.xpath("//div[@class='slAVV4']"));

            List<String> tittle = new ArrayList<>();
            List<String> image = new ArrayList<>();
            List<Integer> review = new ArrayList<>();
            
            for(WebElement card :cardContainerEl ){
                js.executeScript("arguments[0].scrollIntoView(true);", card);
                WebElement reviewsEl = card.findElement(By.xpath(".//span[@class='Wphh3N']"));
                String reviewsStr = reviewsEl.getText().replaceAll("[^\\d]", "");
                int reviews = Integer.parseInt(reviewsStr);

                WebElement titleEl = card.findElement(By.xpath(".//a[@class='wjcEIp']"));
                String tittletxt = titleEl.getText();

                WebElement imgEl = card.findElement(By.xpath(".//img[@class='DByuf4']"));
                String imgLink = imgEl.getAttribute("src");

                tittle.add(tittletxt);
                image.add(imgLink);
                review.add(reviews);

                
            }
            List<Integer> index = new ArrayList<>();
                for(int i=0;i<review.size();i++){
                    index.add(i);
                }

                for(int i=0;i<index.size()-1;i++){
                    for(int j=0;j<index.size()-i-1;j++){
                        if(review.get(index.get(j))<review.get(index.get(j+1))){
                            int temp = index.get(j);
                            index.set(j,index.get(j+1));
                            index.set(j+1,temp);
                        }
                    }
                }
                int limt =Math.min(5,index.size());

                for(int i=0;i<limt;i++){
                    int idx =index.get(i);
                    System.out.println("Tittle : "+ tittle.get(idx));
                    System.out.println("Image URLs : "+ image.get(idx));
                    System.out.println("Review count : "+ review.get(idx));
                }

        }


     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        //  driver.close();
        //  driver.quit();

    }
}