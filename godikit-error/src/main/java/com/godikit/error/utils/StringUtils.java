package com.godikit.error.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Deprecated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    @Deprecated
    public static String defaultIfNull(String message, String def) {
        if (message != null) {
            return message;
        }
        return def;
    }
}
