package com.afterapocalypticcrash.oldStructure.data;

import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllData {

    private static final List<PictureApiContent.Results> ITEMS = new ArrayList<>();
    private static final Map<String, PictureApiContent.Results> ITEM_MAP = new HashMap<>();

    public static Map<String, PictureApiContent.Results> getItemMap() {
        return ITEM_MAP;
    }
    public static List<PictureApiContent.Results> getItemsList(){
        return ITEMS;
    }

    private static String CUR_ID;

    public static String getCurId() {
        return CUR_ID;
    }

    public static void setCurId(String curId) {
        CUR_ID = curId;
    }

    private static String CUR_KEY;

    public static String getCurKey() {
        return CUR_KEY;
    }

    public static void setCurKey(String curKey) {
        CUR_KEY = curKey;
    }

}
