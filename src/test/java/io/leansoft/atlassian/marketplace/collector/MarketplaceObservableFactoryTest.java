package io.leansoft.atlassian.marketplace.collector;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;


public class MarketplaceObservableFactoryTest {
    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testObservableShouldNotReturnErrors() throws Exception {
        final Observable<String> observable = MarketplaceObservableFactory.getObservable();
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }
}