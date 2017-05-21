package filters;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;

public class CustomHttpFiltersSourceAdapter extends HttpFiltersSourceAdapter {
    @Override
    public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
        return new CustomHttpFiltersAdapter(originalRequest);
    }
}