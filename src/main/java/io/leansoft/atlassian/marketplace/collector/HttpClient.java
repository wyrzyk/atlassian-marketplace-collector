package io.leansoft.atlassian.marketplace.collector;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class HttpClient {
    private final OkHttpClient client = new OkHttpClient();

    Response get(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final okhttp3.Response response = client.newCall(request).execute();
        return new Response(response);
    }

    static class Response {
        private final okhttp3.Response response;

        Response(okhttp3.Response response) {
            this.response = response;
        }

        boolean isSuccessful() {
            return response.isSuccessful();
        }

        byte[] getBodyBytes() throws IOException {
            return response.body().bytes();
        }

        int statusCode() {
            return response.code();
        }
    }
}
