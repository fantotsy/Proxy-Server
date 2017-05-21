import certificate.RootCertificateManager;
import filters.CustomHttpFiltersSourceAdapter;
import littleproxy.LittleProxy;
import org.littleshoot.proxy.HttpFiltersSource;

public class Main {
    private static final String ROOT_CERTIFICATE_PATH = "C:\\Users\\Home\\Desktop\\CA\\certificate.cer";
    private static final String PRIVATE_KEY_PATH = "C:\\Users\\Home\\Desktop\\CA\\private-key.pem";
    private static final String PASSWORD_FOR_PRIVATE_KEY = "password";

    public static void main(String[] args) {
        HttpFiltersSource filtersSource = new CustomHttpFiltersSourceAdapter();
        RootCertificateManager rootCertificateManager =
                new RootCertificateManager(ROOT_CERTIFICATE_PATH, PRIVATE_KEY_PATH, PASSWORD_FOR_PRIVATE_KEY);
        LittleProxy littleProxy = new LittleProxy(8087, filtersSource, rootCertificateManager, true);
        littleProxy.start();
    }
}