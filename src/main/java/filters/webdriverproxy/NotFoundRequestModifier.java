package filters.webdriverproxy;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class NotFoundRequestModifier implements RequestFilter {
    private final RequestMatcher requestMatcher;

    public NotFoundRequestModifier(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    @Override
    public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
        if (requestMatcher.matches(contents, messageInfo)) {
            request.setUri("http://dev05-storefront.aws.gha.kfplc.com/shopasdfsdf");
            //TODO implement request modification to get 404.
        }
        return null;
    }
}