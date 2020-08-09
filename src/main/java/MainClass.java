import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        // variables
        String browserURL = "https://www.google.com/";
        String queryString = "automatización";
        String screenShootPath = "C:\\Users\\sca_m\\Pictures\\seleniumPictures\\";

        // init browser
        WebDriver googleDriver = new ChromeDriver();
        googleDriver.manage().window().maximize();

        // Search query
        onSearchQuery(googleDriver, browserURL, queryString);

        // Viewable area Screenshot
        onTakeViewAreaScreenShoot(googleDriver, screenShootPath);

        // Full page Screenshot
        onTakeFullScreenShoot(googleDriver, screenShootPath);

        // Wiki
        //wikiResult(googleDriver);

        // close browser
        //googleDriver.close();


    }

    private static File createFileFromPath(String path, String scType) {

        //Directory
        File directory = new File(path);
        if (!directory.exists()) directory.mkdirs();

        //File Name
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy-HH_mm_ss");
        dateFormat.format(date);
        String fileName = "IMG_" + dateFormat.format(date) + "_" + scType + ".png";

        return new File(directory + "/" + fileName);
    }

    private static void onTakeFullScreenShoot(WebDriver driver, String destPath) {

        Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        try {
            ImageIO.write(sc.getImage(), "PNG", createFileFromPath(destPath, "full"));
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) Desktop.getDesktop().open(new File(destPath));
            else System.out.println("TODO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void onTakeViewAreaScreenShoot(WebDriver driver, String destPath) {
        File scFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scFile, createFileFromPath(destPath, "viewableArea"));
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) Desktop.getDesktop().open(new File(destPath));
            else System.out.println("TODO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void wikiResult(WebDriver driver) {
        // wiki vars
        ArrayList<String> headers = new ArrayList<>();
        List<String> phrase = new ArrayList<>();


        // Wikipedia
        List<WebElement> wikiResult = driver.findElements(By.cssSelector("div.mw-parser-output *"));
        for (WebElement child : wikiResult) {

            if (child.getTagName().equalsIgnoreCase("h3")) {
                String header = child.findElement(By.cssSelector("span.mw-headline")).getText();
                headers.add(header);
                System.out.println(header);

            }
            if (child.getTagName().equalsIgnoreCase("p")) {
                if (child.getText().contains("año")) {
                    String p = child.getText();
                    System.out.println(p);

                    String[] strings = p.split(".");
                    for (String s : strings) {
                        if (s.contains("año")) {
                            phrase.add(s);
                        }
                    }


                }
            }
        }
    }

    private static void onSearchQuery(WebDriver driver, String browserURL, String queryText) {


        // open browser
        driver.get(browserURL);

        // google searchBox
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys(queryText);
        searchBox.submit();

        // get wiki result
        String xpath = "//div[@class=\"g\"]//div[@class=\"r\"]//a[starts-with(@href,\"https://es.wikipedia.org/wiki/\")]";
        driver.findElement(By.xpath(xpath)).click();
        /*WebElement wikiResult = googleDriver.findElement(By.xpath(xpath));
        wikiResult.getAttribute("href");
        wikiResult.click();

        // Method linkText
        try {
            googleDriver.findElement(By.linkText("Wikipedia")).click();
        } catch (Exception e) {
            e.printStackTrace();
            googleDriver.close();
            System.exit(0);
        }*/
    }
}
