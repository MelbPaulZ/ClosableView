package com.developer.paul.closableview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;

/**
 * Created by Paul on 4/5/17.
 */

public class ClosableButtonLinearLayout extends LinearLayout {
    private HashMap<String, Integer> orderHash;
    public ClosableButtonLinearLayout(Context context) {
        super(context);
        init();
    }

    public ClosableButtonLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setOrientation(HORIZONTAL);
        orderHash = new HashMap<>();
    }

    private class ButtonFactory{
//        private HashMap<String, RelativeLayout>

    }
}
