package io.leansoft.atlassian.marketplace.collector;

import io.leansoft.atlassian.marketplace.collector.dto.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import rx.Observable;
import rx.schedulers.Schedulers;

@ComponentScan("io.leansoft.atlassian.marketplace.collector")
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final BeanFactory context = new AnnotationConfigApplicationContext(Main.class);
        final MarketplaceObservableFactory marketplaceObservableFactory = context.getBean(MarketplaceObservableFactory.class);
        final Observable<Plugin> pluginObservable = marketplaceObservableFactory.getObservable()
                .flatMap(plugins -> Observable.from(plugins.getPlugins()));
        pluginObservable
                .subscribeOn(Schedulers.io())
                .subscribe(plugin -> log.info(plugin.toString()));

        pluginObservable.last().subscribe();
    }
}
