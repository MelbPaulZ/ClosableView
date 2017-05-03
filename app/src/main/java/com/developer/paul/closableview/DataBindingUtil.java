package com.developer.paul.closableview;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 3/5/17.
 */

public class DataBindingUtil {
    @BindingAdapter(value = {"orderHashMap", "rowNames","icons", "texts", "clickListeners"})
    public static void setRows(final ClosableLinearLayout closableLinearLayout,
                               HashMap<String, Integer> orderHashMap,
                               final List<String> rowNames,
                               final List<Drawable> icons,
                               final List<String> texts,
                               final List<View.OnClickListener> clickListeners) {
        if (rowNames==null || icons==null || texts==null || clickListeners==null){
            return;
        }
        if (!sameLength(rowNames.size(), icons.size(), texts.size(), clickListeners.size())){
            try {
                throw new Exception("Params length is not same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (closableLinearLayout!=null){
            closableLinearLayout.setOrderHashMap(orderHashMap);
            int size = rowNames.size();
            for (int i = 0 ; i < size ; i++){
                closableLinearLayout.addRow(
                        rowNames.get(i),
                        icons.get(i),
                        texts.get(i),
                        clickListeners.get(i));
            }
            closableLinearLayout.setOnDeleteListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout row = (RelativeLayout) v.getParent();
                    if (row == null ){
                        return;
                    }

                    ViewGroup rowParent = (ViewGroup) row.getParent();
                    if (rowParent == null){
                        return;
                    }

                    int position = rowParent.indexOfChild(row);
                    rowNames.remove(position);
                    icons.remove(position);
                    texts.remove(position);
                    clickListeners.remove(position);
                    closableLinearLayout.removeRow(position);
                    rowParent.removeView(row);
                }
            });
        }
    }

    public static boolean sameLength(int... nums){
        if (nums.length==0){
            return true;
        }
        int curNum = nums[0];
        for (int num: nums){
            if (num!=curNum){
                return false;
            }
        }
        return true;
    }
}
