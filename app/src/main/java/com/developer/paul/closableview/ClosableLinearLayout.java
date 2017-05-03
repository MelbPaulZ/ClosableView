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

    private final int ICON_SIZE = 100;

    private final int TEXT_LEFT_MARGIN = 150;
    private final int TEXT_SIZE = 20;
    private final int TEXT_PADDING = 20;

    private final int CLOSE_RIGHT_MARGIN = 40;
    private final int CLOSE_SIZE = 80;

    private final int ROW_HEIGHT = 200;
    private final int ANIMATION_DURATION = 500;

    private HashMap<String, Integer> orderHashMap;
    private List<String> curViewList;
    private RowFactory rowFactory;

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

    public void addRow(String rowName,
                       Drawable icon,
                       String text,
                       OnClickListener onLeftClickListener){

        RelativeLayout rowLinearLayout = rowFactory.createRow(rowName, icon, text, onLeftClickListener);
        int position  = findPosition(rowName);
        addView(rowLinearLayout, position);
        curViewList.add(position, rowName);
    }

    public void showClosedView(String rowName){
        RelativeLayout rowRelativeLayout = rowFactory.findRow(rowName);
        if (rowRelativeLayout==null){
            return;
        }
        int position  = findPosition(rowName);

        if (rowRelativeLayout.getParent()!=null){
            // if the view is already added, then do nothing
            return;
        }

        addView(rowRelativeLayout, position);
        curViewList.add(position, rowName);

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


    private class RowFactory{

        private HashMap<String, RelativeLayout> rowHashMap;


        public RowFactory() {
            rowHashMap = new HashMap<>();

        }

        public RelativeLayout createRow(String rowName,
                                        Drawable icon,
                                        String text,
                                        OnClickListener onLeftClickListener){
            RelativeLayout row = rowHashMap.get(rowName);
            if (row==null) {
                RelativeLayout rowRelativeLayout = getRelativeLayout();
                addIconView(icon, rowRelativeLayout);
                addDisplayText(text, rowRelativeLayout, onLeftClickListener);
                addClosableView(rowRelativeLayout);
                rowHashMap.put(rowName, rowRelativeLayout);
                row = rowRelativeLayout;
            }
            return row;
        }

        public RelativeLayout findRow(String rowName){
            return rowHashMap.get(rowName);
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
            closeView.setOnClickListener(new OnClickListener() {
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
                    curViewList.remove(position);
                    rowParent.removeView(row);
                }
            });
            rowRelativeLayout.addView(closeView);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }
}