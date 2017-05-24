import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.By;
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
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--proxy-server=http://localhost:8087");
//
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//
//        ChromeDriver driver = new ChromeDriver(capabilities);
//        driver.get("http://dev05-storefront.aws.gha.kfplc.com/checkout/technicalerror/");


        browserMob();
    }

    public static void browserMob(){
        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.addRequestFilter(new RequestFilter() {
            @Override
            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
                if (messageInfo.getOriginalUrl().contains("shop")) {
                    request.setUri("http://dev05-storefront.aws.gha.kfplc.com/shopfhfgh");
                }
                return null;
            }
        });
        proxy.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
               // if (messageInfo.getOriginalUrl().contains("shop")) {
                    //response.setStatus(HttpResponseStatus.NOT_FOUND);
                   // response.headers().add("Test", "test");
              //  }
            }
        });
        proxy.start(8087);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        WebDriver driver = new ChromeDriver(capabilities);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "yahoo.com"
        proxy.newHar("dev05-storefront.aws.gha.kfplc.com");

        // open yahoo.com
        driver.get("http://dev05-storefront.aws.gha.kfplc.com/shop");

        // get the HAR data
        Har har = proxy.getHar();
    }
}