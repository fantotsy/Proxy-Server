import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class WebBrowserMain {
    public static void main(String[] args) {
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Home\\Downloads\\geckodriver.exe");
//
//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("localhost:8087");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PROXY, proxy);
//
//        WebDriver driver = new FirefoxDriver(capabilities);

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Home\\Downloads\\chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server=http://" + "localhost:8087");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.youtube.com/?hl=ru&gl=RU");
    }
}