package io.leansoft.atlassian.marketplace.collector.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static io.leansoft.atlassian.marketplace.collector.utils.Functions.onlyOne;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Plugins {
    private final List<Plugin> plugins;
    private final List<Link> links;

    private Plugins(List<Plugin> plugins, List<Link> links) {
        this.plugins = plugins;
        this.links = links;
    }

    @JsonCreator
    public static Plugins createPlugins(@JsonProperty("plugins") List<Plugin> plugins, @JsonProperty("links") List<Link> links) {
        return new Plugins(plugins, links);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Plugins fromJson(byte[] jsonBytes) throws IOException {
        return mapper.readValue(jsonBytes, Plugins.class);
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public Optional<Link> getNextLink() {
        return links.stream().filter(link -> link.getRel().equals("next") && link.getType().equals("application/json")).reduce(onlyOne());
    }
}
