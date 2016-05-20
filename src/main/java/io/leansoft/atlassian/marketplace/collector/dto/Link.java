package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Link {
    private final String href;
    private final String rel;
    private final String type;

    private Link(String href, String rel, String type) {
        this.href = href;
        this.rel = rel;
        this.type = type;
    }

    @JsonCreator
    public static Link createPlugins(@JsonProperty("href") String href,
                                     @JsonProperty("rel") String rel,
                                     @JsonProperty("type") String type) {
        return new Link(href, rel, type);
    }

    public String getHref() {
        return href;
    }

    public String getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }
}
