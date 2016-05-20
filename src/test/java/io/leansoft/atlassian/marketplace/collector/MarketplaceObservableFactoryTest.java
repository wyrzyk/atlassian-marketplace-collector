package io.leansoft.atlassian.marketplace.collector;

import io.leansoft.atlassian.marketplace.collector.dto.Plugins;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.io.IOException;
import java.util.List;

import static io.leansoft.atlassian.marketplace.collector.Functions.onlyOne;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
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

        final Observable<Plugins> observable = marketplaceObservableFactory.getObservable();
        final TestSubscriber<Plugins> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testObservableShouldHaveLinkToNextResource() throws Exception {
        prepareMockForResponse(ResourcesUtil.getStandardResponse());

        final Observable<Plugins> observable = marketplaceObservableFactory.getObservable();
        final TestSubscriber<Plugins> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        final List<Plugins> onNextEvents = testSubscriber.getOnNextEvents();
        final Plugins plugins = onNextEvents.stream()
                .reduce(onlyOne())
                .get();
        assertThat(plugins.getNextLink()).isPresent();
    }


    private void prepareMockForResponse(byte[] responseBody) throws IOException {
        final HttpClient.Response response = Mockito.mock(HttpClient.Response.class);
        when(response.getBodyBytes()).thenReturn(responseBody);
        when(response.isSuccessful()).thenReturn(true);
        when(httpClient.get(anyString())).thenReturn(response);
    }
}