package filters;

import io.netty.handler.codec.http.*;
import org.littleshoot.proxy.HttpFiltersAdapter;

public class CustomHttpFiltersAdapter extends HttpFiltersAdapter {
    public CustomHttpFiltersAdapter(HttpRequest originalRequest) {
        super(originalRequest);
    }

    @Override
    public HttpResponse clientToProxyRequest(HttpObject httpObject) {

        if (originalRequest.getUri().contains("youtube")) {
            System.out.println(originalRequest.getUri());
            return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        }
        return null;
    }
}