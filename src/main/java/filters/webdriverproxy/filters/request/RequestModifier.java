package filters.webdriverproxy.filters.request;

import filters.webdriverproxy.matchers.request.RequestMatcher;
import net.lightbody.bmp.filters.RequestFilter;

public abstract class RequestModifier implements RequestFilter {
    protected static final int INDEX_OF_HTTP_PROTOCOL_ENDING = 8;

    protected final RequestMatcher requestMatcher;

    protected RequestModifier(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }
}