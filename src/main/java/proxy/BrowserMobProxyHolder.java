package proxy;

import net.lightbody.bmp.BrowserMobProxyServer;

/**
 * Created by Anton_Apanasovych on 5/26/2017.
 */
public class BrowserMobProxyHolder {

    private static BrowserMobProxyServer proxy;

    public static void setProxy(BrowserMobProxyServer proxy) {
        BrowserMobProxyHolder.proxy = proxy;
    }

    public static BrowserMobProxyServer getProxy() {
        return BrowserMobProxyHolder.proxy;
    }

}
