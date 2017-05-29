package filters.webdriverproxy.matchers.request;

import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class RequestMatcher {
    private String containingStringInUrl;
    private String containingStringInBody;
    private HttpMethod usingHttpMethod;

    public RequestMatcher containingStringInUrl(String string) {
        containingStringInUrl = string;
        return this;
    }

    public RequestMatcher containingStringInBody(String string) {
        containingStringInBody = string;
        return this;
    }

    public RequestMatcher usingHttpMethod(HttpMethod httpMethod) {
        usingHttpMethod = httpMethod;
        return this;
    }

    public boolean matches(HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
        return containsStringInUrl(httpMessageInfo) &&
                containsStringInBody(httpMessageContents) &&
                usesHttpMethod(httpMessageInfo);
    }

    private boolean containsStringInUrl(HttpMessageInfo httpMessageInfo) {
        if (containingStringInUrl != null) {
            String url = httpMessageInfo.getOriginalUrl();
            if (!url.contains(containingStringInUrl)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsStringInBody(HttpMessageContents httpMessageContents) {
        if (containingStringInBody != null) {
            String textContents = httpMessageContents.getTextContents();
            if (!textContents.contains(containingStringInBody)) {
                return false;
            }
        }
        return true;
    }

    private boolean usesHttpMethod(HttpMessageInfo httpMessageInfo) {
        if (usingHttpMethod != null) {
            HttpMethod httpMethod = httpMessageInfo.getOriginalRequest().getMethod();
            if (!usingHttpMethod.equals(httpMethod)) {
                return false;
            }
        }
        return true;
    }
}