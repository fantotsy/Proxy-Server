import certificate.RootCertificateManager;
import filters.littleproxy.CustomHttpFiltersSourceAdapter;
import proxy.LittleProxy;
import org.littleshoot.proxy.HttpFiltersSource;

public class Main {
    private static final String ROOT_CERTIFICATE_PATH = "C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\certificate.cer";
    private static final String PRIVATE_KEY_PATH = "C:\\Users\\Anton_Tsymbal\\Desktop\\CA\\private-key.pem";
    private static final String PASSWORD_FOR_PRIVATE_KEY = "password";

    public static void main(String[] args) {

//        // load the root certificate and private key from existing PEM-encoded certificate and private key files
//        PemFileCertificateSource fileCertificateSource = new PemFileCertificateSource(
//                new File("/path/to/my/certificate.cer"),    // the PEM-encoded certificate file
//                new File("/path/to/my/private-key.pem"),    // the PEM-encoded private key file
//                "privateKeyPassword");                      // the password for the private key -- can be null if the private key is not encrypted
//
//
//        // tell the MitmManager to use the custom certificate and private key
//        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
//                .rootCertificateSource(fileCertificateSource)
//                .build();
//
//        // tell the HttpProxyServerBootstrap to use the new MitmManager
//        HttpProxyServer proxyServer = DefaultHttpProxyServer.bootstrap()
//                .withManInTheMiddle(mitmManager)
//                .start();

        HttpFiltersSource filtersSource = new CustomHttpFiltersSourceAdapter();
        RootCertificateManager rootCertificateManager =
                new RootCertificateManager(ROOT_CERTIFICATE_PATH, PRIVATE_KEY_PATH, PASSWORD_FOR_PRIVATE_KEY);
        LittleProxy littleProxy = new LittleProxy(8087, filtersSource, rootCertificateManager, false);
        littleProxy.start();
    }
}