package com.developer.paul.closableview;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 3/5/17.
 */

public class DataBindingUtil {
    @BindingAdapter(value = {"orderHashMap", "rowItems"})
    public static void setRows(final ClosableLinearLayout closableLinearLayout,
                               HashMap<String, Integer> orderHashMap,
                               final List<RowItem> rowItems) {
        if (rowItems == null){
            return;
        }

        if (closableLinearLayout!=null) {
            closableLinearLayout.setOrderHashMap(orderHashMap);
            int size = rowItems.size();
            for (int i = 0; i < size; i++) {
                closableLinearLayout.addRow(rowItems.get(i));
            }
            closableLinearLayout.setOnDeleteListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout row = (RelativeLayout) v.getParent();
                    if (row == null) {
                        return;
                    }

                    ViewGroup rowParent = (ViewGroup) row.getParent();
                    if (rowParent == null) {
                        return;
                    }

                    int position = rowParent.indexOfChild(row);
                    rowItems.remove(position);
                    closableLinearLayout.removeRow(position);
                    rowParent.removeView(row);
                }
            });
        }
    }

    @BindingAdapter(value = {"orderHashMap","buttonItems"})
    public static void setButtons(final ClosableButtonLinearLayout closableButtonLinearLayout
            , HashMap<String, Integer> orderHashMap, final List<ButtonItem> buttonItems){
        if (closableButtonLinearLayout==null){
            return;
        }

        closableButtonLinearLayout.setOrderHash(orderHashMap);
        for (ButtonItem buttonItem: buttonItems){
            closableButtonLinearLayout.addButton(buttonItem);
        }
        closableButtonLinearLayout.setOnDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout btnLayout = (RelativeLayout) v.getParent();
                if (btnLayout==null){
                    return;
                }

                LinearLayout btnLayoutParent = (LinearLayout) btnLayout.getParent();
                if (btnLayoutParent==null){
                    return;
                }

                int position = btnLayoutParent.indexOfChild(btnLayout);
                buttonItems.remove(position);
                closableButtonLinearLayout.remoteButton(position);
                btnLayoutParent.removeView(btnLayout);
            }
        });


    }

}
