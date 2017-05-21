package certificate;

import net.lightbody.bmp.mitm.PemFileCertificateSource;
import net.lightbody.bmp.mitm.RootCertificateGenerator;

import java.io.File;

public class RootCertificateManager {
    private final String rootCertificatePath;
    private final String privateKeyPath;
    private final String passwordForPrivateKey;

    public RootCertificateManager(String rootCertificatePath, String privateKeyPath, String passwordForPrivateKey) {
        this.rootCertificatePath = rootCertificatePath;
        this.privateKeyPath = privateKeyPath;
        this.passwordForPrivateKey = passwordForPrivateKey;
    }

    public PemFileCertificateSource fileCertificateSource() {
        if (!isRootCertificateGenerated()) {
            generateAndSaveNewCertificate();
        }
        return new PemFileCertificateSource(rootCertificateFile(), privateKeyFile(), passwordForPrivateKey);
    }

    private void generateAndSaveNewCertificate() {
        RootCertificateGenerator rootCertificateGenerator = RootCertificateGenerator
                .builder()
                .build();
        rootCertificateGenerator.saveRootCertificateAsPemFile(rootCertificateFile());
        rootCertificateGenerator.savePrivateKeyAsPemFile(privateKeyFile(), passwordForPrivateKey);
    }

    private File rootCertificateFile() {
        return new File(rootCertificatePath);
    }

    private File privateKeyFile() {
        return new File(privateKeyPath);
    }

    private boolean isRootCertificateGenerated() {
        return (rootCertificateFile().exists() && privateKeyFile().exists());
    }
}