package com.developer.paul.closabledatabindingview.closableItem;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.developer.paul.closabledatabindingview.interfaces.ClosableItem;

/**
 * Created by Paul on 4/5/17.
 */

public class RowItem implements ClosableItem {
    private String itemName;
    private Drawable icon;
    private String text;
    private View.OnClickListener clickListener;

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
