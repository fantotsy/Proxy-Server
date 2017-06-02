import filters.webdriverproxy.filters.request.HttpErrors;
import filters.webdriverproxy.filters.request.RequestFilterConfiguration;
import filters.webdriverproxy.filters.request.RequestFilterSupplier;
import filters.webdriverproxy.matchers.request.RequestMatcher;
import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import proxy.BrowserMobProxyHolder;
import proxy.ProxyManager;

import java.util.Arrays;

public class WebBrowserMain {
    public static void main(String[] args) {
//        ProfilesIni prof = new ProfilesIni();
//        FirefoxProfile foxProfile= prof.getProfile ("sslProfile");
//
//        foxProfile.setAcceptUntrustedCertificates(true);
//        foxProfile.setAssumeUntrustedCertificateIssuer(false);

//        ProfilesIni profile = new ProfilesIni();
//        FirefoxProfile myprofile = profile.getProfile("default");
//        myprofile.setAcceptUntrustedCertificates(true);
//        myprofile.setAssumeUntrustedCertificateIssuer(false);


        //capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
        //capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        //capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        // capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        // capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

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

        chrome();
        //firefox();
    }

    public static void chrome() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

//        BrowserMobProxy proxy = new BrowserMobProxyServer();


//        RequestMatcher requestMatcher2 = RequestMatcher.builder()
//                .containingStringInUrl("hello")
//                .usingHttpMethod(HttpMethod.POST).build();
//        proxy.addRequestFilter(new NotFoundRequestModifier(requestMatcher2));
//        proxy.setMitmDisabled(true);
//        proxy.start(0);


        // ChromeOptions options = new ChromeOptions();
        //options.addArguments("--ignore-certificate-errors");
        // capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        BrowserMobProxyServer mobProxyServer = new BrowserMobProxyServer();
        mobProxyServer.setMitmDisabled(true);
        mobProxyServer.start(8089);
        BrowserMobProxyHolder.setProxy(mobProxyServer);
        Proxy proxy = ClientUtil.createSeleniumProxy(mobProxyServer);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        RemoteWebDriver driver = new ChromeDriver(capabilities);
        driver.get("https://dev05-storefront.aws.gha.kfplc.com/customer/login/?prev_page=%2Fshop%3F&isAutoLoggedUser=false");

        driver.getCapabilities();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }

        ProxyManager.getInstance().setRequestFilterSupplier(new RequestFilterSupplier(
                new RequestFilterConfiguration().filterUrlWith("dev05"), HttpErrors.NOT_FOUND));



        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }



        ProxyManager.getInstance().setRequestFilterSupplier(new RequestFilterSupplier(
                new RequestFilterConfiguration().filterUrlWith("dev06"), HttpErrors.NOT_FOUND));
    }

    public static void firefox() {
        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");

        RequestMatcher requestMatcher = RequestMatcher.builder().containingStringInUrl("login").build();

        BrowserMobProxy proxy = new BrowserMobProxyServer();
        //proxy.addRequestFilter(new NotFoundRequestModifier(requestMatcher));
        proxy.setMitmDisabled(true);
        proxy.start(8087);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.get("http://dev05-storefront.aws.gha.kfplc.com/shop");
    }
}