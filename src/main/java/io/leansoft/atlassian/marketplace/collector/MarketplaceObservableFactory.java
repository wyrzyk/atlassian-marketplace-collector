package io.leansoft.atlassian.marketplace.collector;

import io.leansoft.atlassian.marketplace.collector.dto.Link;
import io.leansoft.atlassian.marketplace.collector.dto.Plugins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Optional;

@Component
class MarketplaceObservableFactory {
    private static final int MAX = 100000;
    private final String MARKETPLACE_URL = "https://marketplace.atlassian.com";
    private final String MARKETPLACE_RESOURCE = "/rest/1.0/plugins/search?&hidden=none&hosting=any";

    private final HttpClient httpClient;

    @Autowired
    MarketplaceObservableFactory(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    Observable<Plugins> getObservable() {
        return Observable.create(subscriber -> {
            String link = MARKETPLACE_RESOURCE;
            for (int i = 0; i < MAX; i++) {
                try {
                    final HttpClient.Response response = httpClient.get(MARKETPLACE_URL + link);
                    if (response.isSuccessful()) {
                        final Plugins plugins = Plugins.fromJson(response.getBodyBytes());
                        subscriber.onNext(plugins);
                        final Optional<Link> nextLink = plugins.getNextLink();
                        if (nextLink.isPresent()) {
                            link = nextLink.get().getHref();
                        } else {
                            break;
                        }
                    } else {
                        subscriber.onError(new HTTPException(response.statusCode()));
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                    break;
                }
            }
            subscriber.onCompleted();
        });
    }
}
