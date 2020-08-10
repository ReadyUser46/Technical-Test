package Task2;

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
import java.util.regex.Pattern;

public class Task2 {

    // member variables
    private final String browserURL;
    private final String queryString;
    private final String screenShootPath;
    private final WebDriver driver;

    // constructor
    public Task2(String queryString) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = initBrowser();
        this.queryString = queryString;
        browserURL = "https://www.google.com/";
        screenShootPath = System.getProperty("user.dir") +
                "\\src\\main\\resources\\task2\\seleniumPictures";
    }

    public WebDriver initBrowser() {
        WebDriver googleDriver = new ChromeDriver();
        googleDriver.manage().window().maximize();
        return googleDriver;
    }

    public void onSearchQuery() {
        System.out.println("Searching on Google for " + queryString);

        // open browser
        driver.get(browserURL);

        // google searchBox
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys(queryString);
        searchBox.submit();

    }

    public void onGetWikiResult() {
        System.out.println("Opening the Wikipedia result...");

        // get wiki result
        String xpath = "//div[@class=\"g\"]//div[@class=\"r\"]//a[starts-with(@href,\"https://es.wikipedia.org/wiki/\")]";
        driver.findElement(By.xpath(xpath)).click();

        /*// Method linkText
        try {
            googleDriver.findElement(By.linkText("Wikipedia")).click();
        } catch (Exception e) {
            e.printStackTrace();
            googleDriver.close();
            System.exit(0);
        }*/
    }

    public void onTakeFullScreenShoot() {
        System.out.println("Taking full screenshot...");

        Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        try {
            ImageIO.write(sc.getImage(), "PNG", createFileFromPath(screenShootPath, "full"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTakeViewAreaScreenShoot() {
        System.out.println("Taking the viewable area screenshoot...");

        File scFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scFile, createFileFromPath(screenShootPath, "viewableArea"));
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) Desktop.getDesktop().open(new File(screenShootPath));
            else System.out.println("TODO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCheckFirstAutomationProcess() {
        System.out.println("Checking for the first automation process...\n\n");
        // wiki vars
        List<String> headersList = new ArrayList<>();
        List<String> paragraphList = new ArrayList<>();

        String xpath = "//div[@id=\"mw-content-text\"]//*";
        List<WebElement> webElements = driver.findElements(By.xpath(xpath));
        for (WebElement we : webElements) {
            if (we.getTagName().equals("h3"))
                headersList.add(we.findElement(By.cssSelector("span.mw-headline")).getText());
            else if (we.getTagName().equals("p")) paragraphList.add(we.getText());
        }

        System.out.println("Headers");
        System.out.println(headersList);
        System.out.println("------------------");

        String regex = ".*\\b" + Pattern.quote("año") + "\\b.*";
        for (String str : paragraphList) {
            if (str.contains("1960")) System.out.println(str);
            else if (str.contains("automático") && str.contains("1801")) System.out.println(str);
        }

        // The driver get closed due to last execution
        driver.close();
    }

    private File createFileFromPath(String path, String scType) {

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
}
