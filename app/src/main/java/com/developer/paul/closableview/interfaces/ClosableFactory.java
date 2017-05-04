package com.developer.paul.closableview.interfaces;

import android.view.View;
import android.widget.RelativeLayout;

import com.developer.paul.closableview.closablelayouts.ClosableRelativeLayout;

/**
 * Created by Paul on 4/5/17.
 */

public interface ClosableFactory<T> {
    ClosableRelativeLayout create(T t);

    View.OnClickListener getCloseOnClickListener(T t);
    void setOnDeleteListener(View.OnClickListener onDeleteListener);
}
