package edu.kpdteti.backend.util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchKeyUtil {

    public String capitalizeSearchKey(String searchKey) {
        Matcher matcher = Pattern.compile("\\w+").matcher(searchKey);
        StringBuilder upperSearchKey = new StringBuilder();
        while (matcher.find()) {
            upperSearchKey.append(capitalize(matcher.group()));
            if (!matcher.hitEnd()) {
                upperSearchKey.append(" ");
            }
        }
        return upperSearchKey.toString();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
