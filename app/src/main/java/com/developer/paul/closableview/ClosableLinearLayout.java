package com.developer.paul.closableview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Paul on 3/5/17.
 */

public class ClosableLinearLayout extends LinearLayout{

    private HashMap<String, Integer> orderHashMap;
    private List<String> curViewList;
    private RowFactory rowFactory;
    private OnClickListener onDeleteListener;

    public ClosableLinearLayout(Context context) {
        super(context);
        init();
    }

    public ClosableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setOrientation(LinearLayout.VERTICAL);
        orderHashMap = new HashMap<>();
        curViewList = new ArrayList<>();
        rowFactory = new RowFactory();
    }

    public void addRow(RowItem rowItem){
        RelativeLayout rowLinearLayout = rowFactory.createRow(rowItem);
        int position  = findPosition(rowItem.getRowName());
        if (rowLinearLayout.getParent()==null){
            curViewList.add(position,rowItem.getRowName());
            addView(rowLinearLayout, position);
        }
    }

    public void removeRow(int position){
        curViewList.remove(position);
    }

    private int findPosition(String rowName){
        int len = curViewList.size();
        int rowNumIndex = orderHashMap.get(rowName);
        for (int i = 0 ; i < len ; i++){
            int curViewIndex = orderHashMap.get(curViewList.get(i));
            if (curViewIndex>=rowNumIndex){
                return i;
            }
        }
        return len;
    }

    public void setOrderHashMap(HashMap<String, Integer> orderHashMap) {
        this.orderHashMap = orderHashMap;
    }

    public void setOnDeleteListener(View.OnClickListener onDeleteListener){
        this.onDeleteListener = onDeleteListener;
    }

    private class RowFactory{

        private final int ICON_SIZE = 100;

        private final int TEXT_LEFT_MARGIN = 150;
        private final int TEXT_SIZE = 20;
        private final int TEXT_PADDING = 20;

        private final int CLOSE_RIGHT_MARGIN = 40;
        private final int CLOSE_SIZE = 80;

        private final int ROW_HEIGHT = 200;
        private HashMap<RowItem, RelativeLayout> rowHashMap;


        public RowFactory() {
            rowHashMap = new HashMap<>();

        }

        public RelativeLayout createRow(RowItem rowItem){
            RelativeLayout row = rowHashMap.get(rowItem);
            if (row==null){
                RelativeLayout rowRelativeLayout = getRelativeLayout();
                addIconView(rowItem.getIcon(), rowRelativeLayout);
                addDisplayText(rowItem.getText(), rowRelativeLayout, rowItem.getClickListener());
                addClosableView(rowRelativeLayout);
                rowHashMap.put(rowItem, rowRelativeLayout);
                row = rowRelativeLayout;
            }
            return row;
        }

        private RelativeLayout getRelativeLayout(){
            RelativeLayout rowLinearLayout = new RelativeLayout(getContext());
            rowLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ROW_HEIGHT));
            return rowLinearLayout;
        }

        private void addIconView(Drawable icon, RelativeLayout rowRelativeLayout){
            ImageView iconView = new ImageView(getContext());
            iconView.setImageDrawable(icon);
            RelativeLayout.LayoutParams iconLp = new RelativeLayout.LayoutParams(
                    ICON_SIZE, ICON_SIZE);
            iconLp.addRule(RelativeLayout.CENTER_VERTICAL);
            iconView.setLayoutParams(iconLp);
            rowRelativeLayout.addView(iconView);
        }

        private void addDisplayText(String text, RelativeLayout rowRelativeLayout, OnClickListener leftClickListener){
            TextView displayText = new TextView(getContext());
            displayText.setText(text);
            displayText.setTextSize(TEXT_SIZE);
            displayText.setPadding(TEXT_PADDING, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING);
            displayText.setMinWidth(400);
            displayText.setOnClickListener(leftClickListener);
            RelativeLayout.LayoutParams textLp = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            textLp.leftMargin = TEXT_LEFT_MARGIN;
            textLp.addRule(RelativeLayout.CENTER_VERTICAL);
            displayText.setLayoutParams(textLp);
            rowRelativeLayout.addView(displayText);
        }

        private void addClosableView(RelativeLayout rowRelativeLayout){
            ImageView closeView = new ImageView(getContext());
            closeView.setImageDrawable(getResources().getDrawable(R.drawable.icon_bg_backarrow));
            RelativeLayout.LayoutParams closableViewLp = new RelativeLayout.LayoutParams(CLOSE_SIZE, CLOSE_SIZE);
            closableViewLp.rightMargin = CLOSE_RIGHT_MARGIN;
            closableViewLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            closableViewLp.addRule(RelativeLayout.CENTER_VERTICAL);
            closeView.setLayoutParams(closableViewLp);
            closeView.setOnClickListener(onDeleteListener);
            rowRelativeLayout.addView(closeView);
        }
    }

}
