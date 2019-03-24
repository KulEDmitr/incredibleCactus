package com.afterapocalypticcrash.api;

import java.util.HashMap;
import java.util.Map;

public class AllData {

    private static final Map<String, PictureApiContent.Results> ITEM_MAP = new HashMap<>();

    public static void addItem(PictureApiContent.Results item) {
        ITEM_MAP.put(item.getId(), item);
    }

    public static Map<String, PictureApiContent.Results> getItemMap() {
        return ITEM_MAP;
    }
}
