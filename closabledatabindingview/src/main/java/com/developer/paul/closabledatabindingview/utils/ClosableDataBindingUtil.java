package com.developer.paul.closabledatabindingview.utils;

import com.developer.paul.closabledatabindingview.interfaces.ClosableItem;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 3/5/17.
 */

public class ClosableDataBindingUtil {

    public static <T extends ClosableItem> void sortClosableItem(final HashMap<String, Integer> orderHash, List<T> closableItems){
        Collections.sort(closableItems, new Comparator<ClosableItem>() {
            @Override
            public int compare(ClosableItem o1, ClosableItem o2) {
                return orderHash.get(o1.getItemName()) - orderHash.get(o2.getItemName());
            }
        });
    }

}
