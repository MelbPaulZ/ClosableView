package com.developer.paul.closableview;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Paul on 4/5/17.
 */

public class RowItem {
    private String rowName;
    private Drawable icon;
    private String text;
    private View.OnClickListener clickListener;

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
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
