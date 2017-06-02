package filters.webdriverproxy.filters.request;

import io.netty.handler.codec.http.HttpRequest;

/**
 * Created by Anton_Apanasovych on 5/31/2017.
 */
public interface RequestModifier {

    void filter(HttpRequest httpRequest);
}
