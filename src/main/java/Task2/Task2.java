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

public class Task2 {

    // member variables
    private final String browserURL;
    private final String queryString;
    private String screenShootPath;
    private final WebDriver driver;

    // constructor
    public Task2(String queryString) {
        driver = setupBrowser();
        this.queryString = queryString;
        browserURL = "https://www.google.com/";
    }

    public WebDriver setupBrowser() {

        // Set properties depending on SO: Windows / linux
        if (System.getProperty("os.name").contains("Windows")) {
            screenShootPath = System.getProperty("user.dir") + "\\src\\main\\resources\\task1";
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.ex");
        } else {
            screenShootPath = System.getProperty("user.dir") + "/src/main/resources/task1";
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }

        // init browser
        try {
            WebDriver googleDriver = new ChromeDriver();
            googleDriver.manage().window().maximize();
            return googleDriver;
        } catch (Exception e) {
            System.out.println("You must place the chromedriver into C:\\ if you are using Windows SO,\n"
                    + "or into the root proyect folder if you are using Linux\n"
                    + "Also, ensure your chromedriver mathces your browser version");
        }
        return null;
    }

    public void onSearchQuery() {
        System.out.printf("Searching on Google for \"%s\"\n", queryString);

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
            Desktop.getDesktop().open(new File(screenShootPath));

        } catch (IOException e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }

    public void onCheckFirstAutomationProcess() {
        System.out.println("Checking for the first automation process...");

        // Arraylist for managing the data
        List<String> headersList = new ArrayList<>();
        List<String> phraseList = new ArrayList<>();
        List<String> paragraphList = new ArrayList<>();

        // Get the info from website
        String xpath = "//div[@id=\"mw-content-text\"]//*";
        List<WebElement> webElements = driver.findElements(By.xpath(xpath));
        for (WebElement we : webElements) {
            if (we.getTagName().equals("h3"))
                headersList.add(we.findElement(By.cssSelector("span.mw-headline")).getText());
            else if (we.getTagName().equals("p")) paragraphList.add(we.getText());
        }

        // Clasify info
        for (String str : paragraphList) {
            if (str.contains("1960")) phraseList.add(str);
            else if (str.contains("automático") && str.contains("1801")) phraseList.add(str);
        }

        // Display
        System.out.printf("\n%s:\n  %s", headersList.get(1), phraseList.get(0));
        System.out.printf("\n%s:\n  %s", headersList.get(2), phraseList.get(1));

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

