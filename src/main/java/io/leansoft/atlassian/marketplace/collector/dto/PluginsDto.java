package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PluginsDto {
    List<PluginDto> plugins;

    private static final ObjectMapper mapper = new ObjectMapper();

    public static PluginsDto fromJson(byte[] jsonBytes) throws IOException {
        return mapper.readValue(jsonBytes, PluginsDto.class);
    }
}
