package filters.littleproxy;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.littleshoot.proxy.HttpFiltersAdapter;

import java.net.InetSocketAddress;

public class CustomHttpFiltersAdapter extends HttpFiltersAdapter {
    public CustomHttpFiltersAdapter(HttpRequest originalRequest) {
        super(originalRequest);
    }

//    @Override
//    public HttpResponse proxyToServerRequest(HttpObject httpObject) {
//        if (originalRequest.getUri().contains("technicalerror")) {
//            System.out.println(originalRequest.getUri());
//        }
//        return super.proxyToServerRequest(httpObject);
//    }

//    @Override
//    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
//
//        if (originalRequest.getUri().contains("technicalerror")) {
//            System.out.println(originalRequest.getUri());
//            ((DefaultHttpRequest)httpObject).setUri("http://dev04-storefront.aws.gha.kfplc.com/departments/lighting/DIY779965.cat");
//            return super.clientToProxyRequest(httpObject);
//        }
//        return null;
//    }


    @Override
    public void proxyToServerConnectionSucceeded(ChannelHandlerContext serverCtx) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerConnectionSucceeded");
        }
        super.proxyToServerConnectionSucceeded(serverCtx);
    }

    @Override
    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("clientToProxyRequest");



            //originalRequest.setUri("http://dev04-storefront.aws.gha.kfplc.com/departments/lighting/DIY779965.cat");
        }
        return super.clientToProxyRequest(httpObject);
    }

    @Override
    public HttpResponse proxyToServerRequest(HttpObject httpObject) {


            FullHttpRequest request = (FullHttpRequest) httpObject;

            if (request.getUri().contains("technicalerror")) {
                request.setUri("http://dev04-storefront.aws.gha.kfplc.com/departments/lighting/DIY779965.cat");
            }


        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerRequest");

           // originalRequest.headers().add("Test", "test");
        }

        return super.proxyToServerRequest(request);
    }

    @Override
    public void proxyToServerRequestSending() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerRequestSending");
        }

        super.proxyToServerRequestSending();
    }

    @Override
    public void proxyToServerRequestSent() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerRequestSent");
        }

        super.proxyToServerRequestSent();
    }

    @Override
    public HttpObject serverToProxyResponse(HttpObject httpObject) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("serverToProxyResponse");


        }
//        DefaultHttpResponse response = (DefaultHttpResponse) httpObject;
//
//        if (originalRequest.getUri().contains("technicalerror")) {
//            response.headers().add("Test", "test");
//        }

        return super.serverToProxyResponse(httpObject);
    }

    @Override
    public void serverToProxyResponseTimedOut() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("serverToProxyResponseTimedOut");
        }

        super.serverToProxyResponseTimedOut();
    }

    @Override
    public void serverToProxyResponseReceiving() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("serverToProxyResponseReceiving");
        }

        super.serverToProxyResponseReceiving();
    }

    @Override
    public void serverToProxyResponseReceived() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("serverToProxyResponseReceived");
        }

        super.serverToProxyResponseReceived();
    }

    @Override
    public void proxyToServerConnectionQueued() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerConnectionQueued");
        }

        super.proxyToServerConnectionQueued();
    }

    @Override
    public InetSocketAddress proxyToServerResolutionStarted(String resolvingServerHostAndPort) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerResolutionStarted");
        }

        return super.proxyToServerResolutionStarted(resolvingServerHostAndPort);
    }

    @Override
    public void proxyToServerResolutionFailed(String hostAndPort) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerResolutionFailed");
        }

        super.proxyToServerResolutionFailed(hostAndPort);
    }

    @Override
    public void proxyToServerResolutionSucceeded(String serverHostAndPort, InetSocketAddress resolvedRemoteAddress) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerResolutionSucceeded");
        }

        super.proxyToServerResolutionSucceeded(serverHostAndPort, resolvedRemoteAddress);
    }

    @Override
    public void proxyToServerConnectionStarted() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerConnectionStarted");
        }

        super.proxyToServerConnectionStarted();
    }

    @Override
    public void proxyToServerConnectionSSLHandshakeStarted() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerConnectionSSLHandshakeStarted");
        }

        super.proxyToServerConnectionSSLHandshakeStarted();
    }

    @Override
    public void proxyToServerConnectionFailed() {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToServerConnectionFailed");
        }

        super.proxyToServerConnectionFailed();
    }

    @Override
    public HttpObject proxyToClientResponse(HttpObject httpObject) {
        if (originalRequest.getUri().contains("technicalerror")) {
            System.out.println("proxyToClientResponse");
        }

        if (originalRequest.getUri().contains("departments")) {
            System.out.println("proxyToClientResponse");
        }


        //System.out.println(originalRequest.);


        //if (originalRequest.getUri().contains("technicalerror")) {
            //response.headers().add("Test", "test");
       // }

        //httpObject = (HttpObject) response;

        return super.proxyToClientResponse(httpObject);
    }
}