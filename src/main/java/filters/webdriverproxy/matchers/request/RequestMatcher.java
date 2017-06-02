package filters.webdriverproxy.matchers.request;

import filters.webdriverproxy.filters.request.RequestModifier;
import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;


public final class RequestMatcher {
    private String containingStringInUrl;
    private String containingStringInBody;
    private HttpMethod usingHttpMethod;

    private RequestMatcher(){

    }

    public class RequestMatcherBuilder {
        private RequestMatcherBuilder(){

        }

        public RequestMatcherBuilder containingStringInUrl(String string) {
            RequestMatcher.this.containingStringInUrl = string;
            return this;
        }

        public RequestMatcherBuilder containingStringInBody(String string) {
            RequestMatcher.this.containingStringInBody = string;
            return this;
        }

        public RequestMatcherBuilder usingHttpMethod(HttpMethod httpMethod) {
            RequestMatcher.this.usingHttpMethod = httpMethod;
            return this;
        }

        public RequestMatcher build(){
            return RequestMatcher.this;
        }
    }

    public static RequestMatcherBuilder builder(){
        return new RequestMatcher().new RequestMatcherBuilder();
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