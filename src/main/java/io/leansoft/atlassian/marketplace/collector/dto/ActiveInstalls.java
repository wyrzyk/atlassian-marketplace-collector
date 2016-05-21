package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.String.format;

@JsonIgnoreProperties(ignoreUnknown = true)
class ActiveInstalls {
    private final String totalInstalls;
    private final String totalUsers;

    private ActiveInstalls(String totalInstalls, String totalUsers) {
        this.totalInstalls = totalInstalls;
        this.totalUsers = totalUsers;
    }

    @JsonCreator
    public static ActiveInstalls createPlugins(@JsonProperty("totalInstalls") String totalInstalls,
                                               @JsonProperty("totalUsers") String totalUsers) {
        return new ActiveInstalls(totalInstalls, totalUsers);
    }

    @Override
    public String toString() {
        return format("%s;%s", totalInstalls, totalUsers);
    }
}
