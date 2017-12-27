package com.example.farkas.laundryapp.sample;

/**
 * Created by Farko on 11/15/2017.
 */

import com.example.farkas.laundryapp.model.DataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public static List<DataItem> dataItemList;
    public static Map<String, DataItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new DataItem(null, "shirts", 1,2));
        addItem(new DataItem(null, "socks", 3,4));
        addItem(new DataItem(null, "pants", 5,6));
        addItem(new DataItem(null, "shorts", 7,8));
        addItem(new DataItem(null, "underwear", 9,10));



    }

    private static void addItem(DataItem item) {
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }

}
