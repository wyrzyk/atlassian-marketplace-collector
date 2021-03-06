package io.leansoft.atlassian.marketplace.collector;


import java.io.IOException;

import static org.apache.commons.io.IOUtils.*;

class ResourcesUtil {
    public static byte[] getFirstResponse() throws IOException {
        return getResponseByName("/response1.json");
    }
    public static byte[] getSecondResponse() throws IOException {
        return getResponseByName("/response2.json");
    }
    public static byte[] getLastResponse() throws IOException {
        return getResponseByName("/response3.json");
    }

    private static byte[] getResponseByName(String name) throws IOException {
        return toByteArray(ResourcesUtil.class.getResourceAsStream(name));
    }
}
