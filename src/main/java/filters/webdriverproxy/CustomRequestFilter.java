package filters.webdriverproxy;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class CustomRequestFilter implements RequestFilter {
    @Override
    public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
        if (messageInfo.getOriginalUrl().contains("channel")) {
            request.setUri("https://www.youtube.com/channelfdgdfh/");
        }
        return null;
    }
}