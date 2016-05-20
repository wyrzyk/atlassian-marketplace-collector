package io.leansoft.atlassian.marketplace.collector;

import java.util.function.BinaryOperator;

public class Functions {
    public static <T> BinaryOperator<T> onlyOne() {
        return (o1, o2) -> {
            throw new IllegalStateException("There should be only one object.");
        };
    }
}
