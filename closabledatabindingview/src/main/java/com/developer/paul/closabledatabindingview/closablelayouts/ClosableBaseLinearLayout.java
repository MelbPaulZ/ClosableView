package com.developer.paul.closabledatabindingview.closablelayouts;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableList;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.developer.paul.closabledatabindingview.interfaces.ClosableFactory;
import com.developer.paul.closabledatabindingview.interfaces.ClosableItem;
import com.developer.paul.closabledatabindingview.utils.ClosableDataBindingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 4/5/17.
 */

@BindingMethods({
        @BindingMethod(type = ClosableBaseLinearLayout.class, attribute = "ClosableBaseLinearLayout:orderHashMap", method = "setOrderHash"),
        @BindingMethod(type = ClosableBaseLinearLayout.class, attribute = "ClosableBaseLinearLayout:closableItems", method = "addList")
})
public abstract class ClosableBaseLinearLayout extends LinearLayout {
    protected ClosableFactory closableFactory;
    protected HashMap<String, Integer> orderHash;
    protected List<ClosableItem> curViewList;
    protected List<? extends ClosableItem> originList;


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
        closableFactory.setOnDeleteListener(onDeleteListener());
    }

    private OnClickListener onDeleteListener(){
        return new View.OnClickListener() {
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
                curViewList.remove(item);
                remove(item);
                btnLayoutParent.removeView(btnLayout);
            }
        };
    }

    public void setOrderHash(HashMap<String, Integer> orderHash) {
        this.orderHash = orderHash;
    }

    public void setOnDeleteListener(OnClickListener onDeleteListener) {
        this.closableFactory.setOnDeleteListener(onDeleteListener);
    }

    public void addList(ObservableList<? extends ClosableItem> tList){
        ClosableDataBindingUtil.sortClosableItem(orderHash, tList);
        originList = tList;
        for (ClosableItem item: tList){
            add(item);
        }
    }

    public void add(ClosableItem t){
        ClosableRelativeLayout btnLayout = closableFactory.create(t);
        if (btnLayout.getParent()==null){
            int position = findPosition(t.getItemName());
            addView(btnLayout, position);
            curViewList.add(position, t);
        }
    }

    public void remove(ClosableItem t){
        curViewList.remove(t);
        originList.remove(t);
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
