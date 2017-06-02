package filters.webdriverproxy.filters.request;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import proxy.ProxyManager;

public class RequestFilterSupplier implements RequestFilter {

    private final RequestFilterConfiguration requestFilterConfiguration;
    private RequestModifier requestModifier;

    public RequestFilterSupplier(RequestFilterConfiguration requestFilterConfiguration, RequestModifier requestModifier) {
        this.requestFilterConfiguration = requestFilterConfiguration;
        this.requestModifier = requestModifier;
    }

    @Override
    public HttpResponse filterRequest(HttpRequest httpRequest, HttpMessageContents httpMessageContents,
                                      HttpMessageInfo httpMessageInfo) {
        if(isCurrentRequestFilterSupplierActive()) {
            if (requestFilterConfiguration.applyFor(httpMessageContents, httpMessageInfo)) {
                requestModifier.filter(httpRequest);
            }
        }
        return null;
    }

    private boolean isCurrentRequestFilterSupplierActive(){
        RequestFilterSupplier activeRequestFilterSupplier = ProxyManager.getInstance().getRequestFilterSupplier();
        return (this.equals(activeRequestFilterSupplier));
    }

}