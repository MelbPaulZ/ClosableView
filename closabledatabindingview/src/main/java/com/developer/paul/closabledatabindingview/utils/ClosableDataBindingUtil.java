package com.developer.paul.closabledatabindingview.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.LinearLayout;

import com.developer.paul.closabledatabindingview.closablelayouts.ClosableRelativeLayout;
import com.developer.paul.closabledatabindingview.interfaces.ClosableItem;
import com.developer.paul.closabledatabindingview.closablelayouts.ClosableBaseLinearLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 3/5/17.
 */

public class ClosableDataBindingUtil {

    @BindingAdapter(value = {"orderHashMap","items"})
    public static <T extends ClosableItem> void setButtons(final ClosableBaseLinearLayout closableButtonLinearLayout
            , HashMap<String, Integer> orderHashMap, final List<T> items){
        if (closableButtonLinearLayout==null){
            return;
        }

        closableButtonLinearLayout.setOrderHash(orderHashMap);
        sortClosableItem(orderHashMap, items);
        for (T t: items){
            closableButtonLinearLayout.add(t);
        }
        closableButtonLinearLayout.setOnDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClosableRelativeLayout btnLayout = (ClosableRelativeLayout) v.getParent();
                if (btnLayout==null){
                    return;
                }

                LinearLayout btnLayoutParent = (LinearLayout) btnLayout.getParent();
                if (btnLayoutParent==null){
                    return;
                }

                ClosableItem item = btnLayout.getClosableItem();
                items.remove(item);
                closableButtonLinearLayout.remove(item);
                btnLayoutParent.removeView(btnLayout);
            }
        });
    }

    public static <T extends ClosableItem> void sortClosableItem(final HashMap<String, Integer> orderHash, List<T> closableItems){
        Collections.sort(closableItems, new Comparator<ClosableItem>() {
            @Override
            public int compare(ClosableItem o1, ClosableItem o2) {
                return orderHash.get(o1.getItemName()) - orderHash.get(o2.getItemName());
            }
        });
    }

}
