package com.cathaybk.newbiehomework.util;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {

    public static String convertFullWidthToHalfWidth(String input) {
        if (input == null || input.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == '\u3000') {
                result.append('\u0020');
            } else if (c >= '\uFF01' && c <= '\uFF5E') {
                result.append((char) (c - 0xFEE0));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
