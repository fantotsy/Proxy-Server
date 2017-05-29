package filters.webdriverproxy.filters.request.impl;

import filters.webdriverproxy.filters.request.RequestModifier;
import filters.webdriverproxy.matchers.request.RequestMatcher;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class NotFoundRequestModifier extends RequestModifier {
    private static final String RESOURCE_NOT_FOUND_PATH = "/resource_not_found_error";

    public NotFoundRequestModifier(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }

    @Override
    public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
        if (requestMatcher.matches(contents, messageInfo)) {
            String uri = request.getUri();
            int indexOfHostnamePortEnding = uri.indexOf("/", INDEX_OF_HTTP_PROTOCOL_ENDING);
            String protocolHostnamePort = uri.substring(0, indexOfHostnamePortEnding);
            String notFoundUrl = protocolHostnamePort + RESOURCE_NOT_FOUND_PATH;
            request.setUri(notFoundUrl);
        }
        return null;
    }
}