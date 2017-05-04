package com.developer.paul.closableview;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Paul on 4/5/17.
 */

public class ButtonItem {
    private String buttonName;
    private Drawable icon;
    private View.OnClickListener onClickListener;


    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
