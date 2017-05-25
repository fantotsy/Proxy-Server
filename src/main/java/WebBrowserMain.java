import certificate.CustomMitmManager;
import filters.webdriverproxy.CustomRequestFilter;
import filters.webdriverproxy.CustomResponseFilter;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.mitm.PemFileCertificateSource;
import net.lightbody.bmp.mitm.RootCertificateGenerator;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import proxy.WebDriverProxy;

import java.io.File;

public class WebBrowserMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");

//        Proxy seleniumProxy = new WebDriverProxy()
//                .withRequestFilter(new CustomRequestFilter())
//                .withResponseFilter(new CustomResponseFilter())
//               // .withMitmManager()
//                .start(8087);

//        BrowserMobProxy seleniumProxy = new WebDriverProxy()
//                .withRequestFilter(new CustomRequestFilter())
//                .withResponseFilter(new CustomResponseFilter())
//                //.withMitmManager()
//                .start(8087);
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);




        RootCertificateGenerator rootCertificateGenerator = RootCertificateGenerator.builder().build();

        // save the newly-generated Root Certificate and Private Key -- the .cer file can be imported
        // directly into a browser
        rootCertificateGenerator.saveRootCertificateAsPemFile(new File("C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\certificate.cer"));
        rootCertificateGenerator.savePrivateKeyAsPemFile(new File("C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\private-key.pem"), "password");

        PemFileCertificateSource fileCertificateSource = new PemFileCertificateSource(
                new File("C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\certificate.cer"),    // the PEM-encoded certificate file
                new File("C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\private-key.pem"),    // the PEM-encoded private key file
                "password");                      // the password for the private key -- can be null if the private key is not encrypted


        // tell the MitmManager to use the custom certificate and private key
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
                .rootCertificateSource(fileCertificateSource)
                .build();


        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setMitmManager(mitmManager);
        proxy.start(8087);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        WebDriver driver = new FirefoxDriver(capabilities);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "yahoo.com"
        proxy.newHar("yahoo.com");

        // open yahoo.com
        driver.get("http://dev05-storefront.aws.gha.kfplc.com/shop");

        // get the HAR data
        Har har = proxy.getHar();



//        WebDriver driver = new ChromeDriver(capabilities);
//        driver.get("http://dev05-storefront.aws.gha.kfplc.com/shop");



//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("localhost:8087");
//        proxy.setSslProxy("localhost:8087");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PROXY, proxy);
//
//        WebDriver driver = new ChromeDriver(capabilities);

//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Home\\Downloads\\chromedriver.exe");
//
//        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--proxy-server=http://" + "localhost:8087");
//        WebDriver driver = new ChromeDriver(option);
//
       // driver.get("https://www.youtube.com/?hl=ru&gl=RU");

    }
}