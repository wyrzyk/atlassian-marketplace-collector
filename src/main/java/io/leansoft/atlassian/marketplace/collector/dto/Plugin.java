package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.String.format;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Plugin {
    private final Integer downloadCount;
    private final String name;
    private final String pluginKey;
    private final ActiveInstalls activeInstalls;

    private Plugin(Integer downloadCount, String name, String pluginKey, ActiveInstalls activeInstalls) {
        this.downloadCount = downloadCount;
        this.name = name;
        this.pluginKey = pluginKey;
        this.activeInstalls = activeInstalls;
    }

    @JsonCreator
    public static Plugin createPlugin(@JsonProperty("downloadCount") Integer downloadCount,
                                      @JsonProperty("name") String name,
                                      @JsonProperty("pluginKey") String pluginKey,
                                      @JsonProperty("activeInstalls") ActiveInstalls activeInstalls) {
        return new Plugin(downloadCount, name, pluginKey, activeInstalls);
    }

    @Override
    public String toString() {
        return format("Plugin;%s;%s;%s;%s", name, pluginKey, downloadCount, activeInstalls);
    }
}
