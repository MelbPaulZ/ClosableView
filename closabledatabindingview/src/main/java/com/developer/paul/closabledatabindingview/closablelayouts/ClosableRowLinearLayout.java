package com.developer.paul.closabledatabindingview.closablelayouts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developer.paul.closabledatabindingview.R;
import com.developer.paul.closabledatabindingview.closableItem.RowItem;
import com.developer.paul.closabledatabindingview.interfaces.ClosableFactory;

import java.util.HashMap;

/**
 * Created by Paul on 3/5/17.
 */

public class ClosableRowLinearLayout extends ClosableBaseLinearLayout {


    public ClosableRowLinearLayout(Context context) {
        super(context);
        init();
    }

    public ClosableRowLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setOrientation(VERTICAL);
    }

    @Override
    protected ClosableFactory getFactory() {
        return new RowFactory();
    }

    public void setOnDeleteListener(View.OnClickListener onDeleteListener){
         closableFactory.setOnDeleteListener(onDeleteListener);
    }


    private class RowFactory implements ClosableFactory<RowItem>{
        private final int ICON_SIZE = 100;

        private final int TEXT_LEFT_MARGIN = 150;
        private final int TEXT_SIZE = 20;
        private final int TEXT_PADDING = 20;

        private final int CLOSE_RIGHT_MARGIN = 40;
        private final int CLOSE_SIZE = 80;

        private final int ROW_HEIGHT = 200;
        private HashMap<RowItem, ClosableRelativeLayout> rowHashMap;
        private OnClickListener onDeleteListener;

        RowFactory() {
            rowHashMap = new HashMap<>();
        }


        @Override
        public ClosableRelativeLayout create(RowItem rowItem) {
            ClosableRelativeLayout row = rowHashMap.get(rowItem);
            if (row==null){
                ClosableRelativeLayout rowRelativeLayout = getRelativeLayout(rowItem);
                addIconView(rowItem.getIcon(), rowRelativeLayout);
                addDisplayText(rowItem.getText(), rowRelativeLayout, rowItem.getClickListener());
                addClosableView(rowRelativeLayout);
                rowHashMap.put(rowItem, rowRelativeLayout);
                row = rowRelativeLayout;
            }
            return row;
        }

        @Override
        public OnClickListener getCloseOnClickListener(RowItem rowItem) {
            return onDeleteListener;
        }

        @Override
        public void setOnDeleteListener(OnClickListener onDeleteListener) {
            this.onDeleteListener = onDeleteListener;
        }


        private ClosableRelativeLayout getRelativeLayout(RowItem rowItem){
            ClosableRelativeLayout rowLayout = new ClosableRelativeLayout(getContext());
            rowLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ROW_HEIGHT));
            rowLayout.setClosableItem(rowItem);
            return rowLayout;
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
            closeView.setImageDrawable(getResources().getDrawable(R.drawable.test_backarrow));
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
