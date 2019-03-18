package com.example.hw_3.pictureItems;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PictureContent {

    public static final List<PictureItem> ITEMS = new ArrayList<>();
    public static final Map<String, PictureItem> ITEM_MAP = new HashMap<>();



    public static void addItem(PictureItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static PictureItem createPictureItem(int position, String description, String preview) {
        return new PictureItem(String.valueOf(position), description, preview);
    }

    public static class PictureItem {
        public final String id;
        public final String description;
        public final String preview;

        PictureItem(String id, String description, String preview) {
            this.id = id;
            this.description = description;
            this.preview = preview;
        }

        @NonNull
        @Override
        public String toString() {
            return description;
        }
    }
}