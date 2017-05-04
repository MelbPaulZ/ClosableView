package com.developer.paul.closableview.closablelayouts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.developer.paul.closableview.interfaces.ClosableFactory;
import com.developer.paul.closableview.interfaces.ClosableItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 4/5/17.
 */

public abstract class ClosableBaseLinearLayout<T extends ClosableItem> extends LinearLayout {
    protected ClosableFactory closableFactory;
    protected HashMap<String, Integer> orderHash;
    protected List<T> curViewList;


    public ClosableBaseLinearLayout(Context context) {
        super(context);
        init();
    }

    public ClosableBaseLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        orderHash = new HashMap<>();
        curViewList = new ArrayList<>();
        closableFactory = getFactory();
    }

    public void setOrderHash(HashMap<String, Integer> orderHash) {
        this.orderHash = orderHash;
    }

    public void setOnDeleteListener(OnClickListener onDeleteListener) {
        this.closableFactory.setOnDeleteListener(onDeleteListener);
    }

    public void add(T t){
        ClosableRelativeLayout btnLayout = closableFactory.create(t);
        if (btnLayout.getParent()==null){
            int position = findPosition(t.getItemName());
            addView(btnLayout, position);
            curViewList.add(position, t);
        }
    }

    public void remove(T t){
        curViewList.remove(t);
    }

    private int findPosition(String rowName){
        int len = curViewList.size();
        int rowNumIndex = orderHash.get(rowName);
        for (int i = 0 ; i < len ; i++){
            int curViewIndex = orderHash.get(curViewList.get(i).getItemName());
            if (curViewIndex>=rowNumIndex){
                return i;
            }
        }
        return len;
    }

    protected abstract ClosableFactory getFactory();

}
