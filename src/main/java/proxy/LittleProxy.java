package proxy;

import certificate.RootCertificateManager;
import net.lightbody.bmp.mitm.PemFileCertificateSource;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import org.apache.log4j.BasicConfigurator;
import org.littleshoot.proxy.*;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

public class LittleProxy {
    private final int port;
    private final HttpFiltersSource filtersSource;
    private final RootCertificateManager rootCertificateManager;
    private final boolean enableLog4j;

    private HttpProxyServer httpProxyServer;
    private ImpersonatingMitmManager mitmManager;

    public LittleProxy(int port, HttpFiltersSource filtersSource, RootCertificateManager rootCertificateManager, boolean enableLog4j) {
        this.port = port;
        this.filtersSource = filtersSource;
        this.rootCertificateManager = rootCertificateManager;
        this.enableLog4j = enableLog4j;
    }

    public void start() {
        configureLog4j();
        configureRootCertificate();
        httpProxyServer = DefaultHttpProxyServer.bootstrap()
                .withPort(port)
                .withFiltersSource(filtersSource)
                .withManInTheMiddle(mitmManager)
                .start();
    }

    public void abort() {
        httpProxyServer.abort();
    }

    private void configureLog4j() {
        if (enableLog4j) {
            BasicConfigurator.configure();
        }
    }

    private void configureRootCertificate() {
        PemFileCertificateSource fileCertificateSource = rootCertificateManager.fileCertificateSource();
        mitmManager = ImpersonatingMitmManager.builder().rootCertificateSource(fileCertificateSource).build();
    }
}