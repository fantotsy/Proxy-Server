package proxy;

import certificate.RootCertificateManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.mitm.PemFileCertificateSource;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import org.littleshoot.proxy.MitmManager;
import org.openqa.selenium.Proxy;

public class WebDriverProxy {
    private final BrowserMobProxy proxy;

    public WebDriverProxy() {
        proxy = new BrowserMobProxyServer();
    }

    public WebDriverProxy withRequestFilter(RequestFilter requestFilter) {
        proxy.addRequestFilter(requestFilter);
        return this;
    }

    public WebDriverProxy withResponseFilter(ResponseFilter responseFilter) {
        proxy.addResponseFilter(responseFilter);
        return this;
    }

    public WebDriverProxy withMitmManager() {
        RootCertificateManager rootCertificateManager =
                new RootCertificateManager("C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\certificate.cer",
                        "C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\private-key.pem", "password");
        PemFileCertificateSource fileCertificateSource = rootCertificateManager.fileCertificateSource();
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder().rootCertificateSource(fileCertificateSource).build();
        proxy.setMitmManager(mitmManager);
        return this;
    }

    public BrowserMobProxy start(int port) {
        proxy.start(port);
        return proxy;
        //return ClientUtil.createSeleniumProxy(proxy);
    }

    public void abort() {
        proxy.abort();
    }

}