package io.leansoft.atlassian.marketplace.collector;


import java.io.IOException;

import static org.apache.commons.io.IOUtils.*;

class ResourcesUtil {
    public static byte[] getStandardResponse() throws IOException {
        return getResponseByName("/response1.json");
    }

    private static byte[] getResponseByName(String name) throws IOException {
        return toByteArray(ResourcesUtil.class.getResourceAsStream(name));
    }
}
