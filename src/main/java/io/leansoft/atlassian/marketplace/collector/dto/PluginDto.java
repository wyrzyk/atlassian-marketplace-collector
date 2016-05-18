package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PluginDto {
    Integer downloadCount;
    String name;
    String status;
}
