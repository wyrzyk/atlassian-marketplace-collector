package io.leansoft.atlassian.marketplace.collector;

import io.leansoft.atlassian.marketplace.collector.dto.Plugins;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

@Component
public class MarketplaceObservableFactory {
    private final String MARKETPLACE_LINK_FORMAT = "https://marketplace.atlassian.com/rest/1.0/plugins/search?offset=%d&hidden=none&hosting=any";

    private final HttpClient httpClient;

    public MarketplaceObservableFactory(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Observable<Plugins> getObservable() {
        return Observable.create(subscriber -> {
            try {

                final HttpClient.Response response = httpClient.get(String.format(MARKETPLACE_LINK_FORMAT, 0));
                if (response.isSuccessful()) {
                    final Plugins plugins = Plugins.fromJson(response.getBodyBytes());
                    subscriber.onNext(plugins);
                } else {
                    subscriber.onError(new HTTPException(response.statusCode()));
                }
            } catch (IOException e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }
}
