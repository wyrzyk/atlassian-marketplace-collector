package io.leansoft.atlassian.marketplace.collector;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

public class MarketplaceObservableFactory {
    private static final String MARKETPLACE_LINK_FORMAT = "https://marketplace.atlassian.com/rest/1.0/plugins/search?offset=%d&hidden=none&hosting=any";
    private static final OkHttpClient client = new OkHttpClient();

    public static Observable<String> getObservable() {
        return Observable.create(subscriber -> {
            final Request request = new Request.Builder()
                    .url(String.format(MARKETPLACE_LINK_FORMAT, 0))
                    .build();
            try {
                final Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    subscriber.onNext(response.body().string());
                } else {
                    subscriber.onError(new HTTPException(response.code()));
                }
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }
}