package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Plugin {
    private final Integer downloadCount;
    private final String name;
    private final String status;

    private Plugin(Integer downloadCount, String name, String status) {
        this.downloadCount = downloadCount;
        this.name = name;
        this.status = status;
    }

    @JsonCreator
    public static Plugin createPlugins(@JsonProperty("downloadCount") Integer downloadCount,
                                       @JsonProperty("name") String name,
                                       @JsonProperty("status") String status) {
        return new Plugin(downloadCount, name, status);
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
