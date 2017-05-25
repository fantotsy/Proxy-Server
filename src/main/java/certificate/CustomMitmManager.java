package certificate;

import io.netty.handler.codec.http.HttpRequest;
import org.littleshoot.proxy.MitmManager;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;

public class CustomMitmManager implements MitmManager {
    @Override
    public SSLEngine serverSslEngine(String peerHost, int peerPort) {
        return null;
    }

    @Override
    public SSLEngine serverSslEngine() {
        return null;
    }

    @Override
    public SSLEngine clientSslEngineFor(HttpRequest httpRequest, SSLSession serverSslSession) {
        return null;
    }
}