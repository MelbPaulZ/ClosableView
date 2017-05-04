package com.developer.paul.closableview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 4/5/17.
 */

public class ClosableButtonLinearLayout extends LinearLayout {
    private List<String> curViewList;
    private HashMap<String, Integer> orderHash;
    private ButtonFactory buttonFactory;
    private OnClickListener onDeleteListener;
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
        curViewList = new ArrayList<>();
        buttonFactory = new ButtonFactory();
    }

    public void addButton(ButtonItem buttonItem){
        RelativeLayout btnLayout = buttonFactory.createButton(buttonItem);
        if (btnLayout.getParent()==null){
            int position = findPosition(buttonItem.getButtonName());
            addView(btnLayout, position);
            curViewList.add(position, buttonItem.getButtonName());
        }
    }

    public void remoteButton(int position){
        curViewList.remove(position);
    }

    public List<String> getCurViewList() {
        return curViewList;
    }

    public void setCurViewList(List<String> curViewList) {
        this.curViewList = curViewList;
    }

    public HashMap<String, Integer> getOrderHash() {
        return orderHash;
    }

    public OnClickListener getOnDeleteListener() {
        return onDeleteListener;
    }

    public void setOnDeleteListener(OnClickListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setOrderHash(HashMap<String, Integer> orderHash) {
        this.orderHash = orderHash;
    }

    private int findPosition(String rowName){
        int len = curViewList.size();
        int rowNumIndex = orderHash.get(rowName);
        for (int i = 0 ; i < len ; i++){
            int curViewIndex = orderHash.get(curViewList.get(i));
            if (curViewIndex>=rowNumIndex){
                return i;
            }
        }
        return len;
    }

    private class ButtonFactory{

        private final int BUTTON_LAYOUT_HEIGHT = 100;
        private final int ICON_SIZE = 80;
        private final int ICON_PADDING = 10;

        private HashMap<ButtonItem, RelativeLayout> buttonHashMap;

        public ButtonFactory() {
            buttonHashMap = new HashMap<>();
        }

        public RelativeLayout createButton(ButtonItem buttonItem){
            RelativeLayout button = buttonHashMap.get(buttonItem);
            if (button==null){
                RelativeLayout newBtnLayout = getButtonLayout();
                addIcon(buttonItem.getIcon(), newBtnLayout, onDeleteListener);
                buttonHashMap.put(buttonItem, newBtnLayout);
                button = newBtnLayout;
            }
            return button;

        }

        private RelativeLayout getButtonLayout(){
            RelativeLayout newBtnLayout = new RelativeLayout(getContext());
            LayoutParams newBtnLp = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, BUTTON_LAYOUT_HEIGHT);
            newBtnLp.weight = 1;
            newBtnLayout.setLayoutParams(newBtnLp);
            return newBtnLayout;
        }

        private void addIcon(Drawable icon, RelativeLayout layout, OnClickListener onClickListener){
            ImageView iconImage = new ImageView(layout.getContext());
            iconImage.setImageDrawable(icon);
            RelativeLayout.LayoutParams iconLp = new RelativeLayout.LayoutParams(ICON_SIZE, ICON_SIZE);
            iconLp.addRule(RelativeLayout.CENTER_IN_PARENT);
            iconImage.setLayoutParams(iconLp);
            iconImage.setPadding(ICON_PADDING, ICON_PADDING, ICON_PADDING, ICON_PADDING);
            iconImage.setOnClickListener(onClickListener);
            layout.addView(iconImage);
        }

    }
}
