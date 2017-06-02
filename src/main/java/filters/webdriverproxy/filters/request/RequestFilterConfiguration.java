package filters.webdriverproxy.filters.request;

import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class RequestFilterConfiguration {

    private String url = "";
    private String body = "";
    private HttpMethod httpMethod;

    public RequestFilterConfiguration filterUrlWith(String urlPartition) {
        this.url = urlPartition;
        return this;
    }

    public RequestFilterConfiguration filterBodyWith(String bodyPartition) {
        this.body = bodyPartition;
        return this;
    }

    public RequestFilterConfiguration filterMethodWith(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public boolean applyFor(HttpMessageContents httpMessageContents,
                         HttpMessageInfo httpMessageInfo) {
        return httpMessageInfo.getOriginalUrl().contains(getUrl()) &&
                httpMessageContents.getTextContents().contains(getBody()) &&
                (httpMessageInfo.getOriginalRequest().getMethod().equals(getHttpMethod()) ||
                        getHttpMethod() == null);
    }

}