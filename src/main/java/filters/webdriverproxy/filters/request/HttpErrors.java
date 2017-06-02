package filters.webdriverproxy.filters.request;

import io.netty.handler.codec.http.HttpRequest;

public enum HttpErrors implements RequestModifier {
    NOT_FOUND {
        private static final String RESOURCE_NOT_FOUND_PATH = "/resource_not_found_error";
        private static final int INDEX_OF_HOSTNAME_LEADING_CHARACTER = 8;

        @Override
        public void filter(HttpRequest httpRequest) {
            String uri = httpRequest.getUri();
            int indexOfHostnamePortEnding = uri.indexOf("/", INDEX_OF_HOSTNAME_LEADING_CHARACTER);
            String protocolHostnamePort = uri.substring(0, indexOfHostnamePortEnding);
            String notFoundUrl = protocolHostnamePort + RESOURCE_NOT_FOUND_PATH;
            httpRequest.setUri(notFoundUrl);
        }
    };
}