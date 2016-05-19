package io.leansoft.atlassian.marketplace.collector;

import io.leansoft.atlassian.marketplace.collector.dto.PluginsDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.io.IOException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;


public class MarketplaceObservableFactoryTest {
    private MarketplaceObservableFactory marketplaceObservableFactory;
    @Mock
    private HttpClient httpClient;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        marketplaceObservableFactory = new MarketplaceObservableFactory(httpClient);
    }

    @Test
    public void testObservableShouldNotReturnErrors() throws Exception {
        prepareMockForResponse(ResourcesUtil.getStandardResponse());

        final Observable<PluginsDto> observable = marketplaceObservableFactory.getObservable();
        final TestSubscriber<PluginsDto> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }

    private void prepareMockForResponse(byte[] responseBody) throws IOException {
        final HttpClient.Response response = Mockito.mock(HttpClient.Response.class);
        when(response.getBodyBytes()).thenReturn(responseBody);
        when(response.isSuccessful()).thenReturn(true);
        when(httpClient.get(anyString())).thenReturn(response);
    }
}