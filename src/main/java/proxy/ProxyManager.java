package proxy;

import filters.webdriverproxy.filters.request.RequestFilterSupplier;
import net.lightbody.bmp.BrowserMobProxyServer;

/**
 * Created by Anton_Apanasovych on 5/29/2017.
 */
public class ProxyManager {
    private static ProxyManager proxyManager;
    private BrowserMobProxyServer proxy;
    private RequestFilterSupplier requestFilterSupplier;

    private ProxyManager() {
        this.proxy = BrowserMobProxyHolder.getProxy();
    }

    public static ProxyManager getInstance() {
        if (proxyManager == null) {
            proxyManager = new ProxyManager();
        }
        return proxyManager;
    }

    public void start() {
        proxy.start();
    }

    public void setRequestFilterSupplier(RequestFilterSupplier requestFilterSupplier) {
        proxy.addRequestFilter(requestFilterSupplier);
        this.requestFilterSupplier = requestFilterSupplier;
    }

    public RequestFilterSupplier getRequestFilterSupplier(){
        return requestFilterSupplier;
    }

    public void stop() {
        proxy.stop();
    }
}