package com.developer.paul.closableview.closablelayouts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.developer.paul.closableview.closableItem.ButtonItem;
import com.developer.paul.closableview.interfaces.ClosableFactory;

import java.util.HashMap;

/**
 * Created by Paul on 4/5/17.
 */

public class ClosableButtonLinearLayout extends ClosableBaseLinearLayout<ButtonItem> {
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
    }

    @Override
    protected ClosableFactory getFactory() {
        return new ButtonFactory();
    }

    private class ButtonFactory implements ClosableFactory<ButtonItem> {
        private final int BUTTON_LAYOUT_HEIGHT = 100;
        private final int ICON_SIZE = 80;
        private final int ICON_PADDING = 10;
        private OnClickListener onDeleteListener;

        private HashMap<ButtonItem, ClosableRelativeLayout> buttonHashMap;

        public ButtonFactory() {
            buttonHashMap = new HashMap<>();
        }

        @Override
        public ClosableRelativeLayout create(ButtonItem buttonItem) {
            ClosableRelativeLayout button = buttonHashMap.get(buttonItem);
            if (button==null){
                ClosableRelativeLayout newBtnLayout = getButtonLayout(buttonItem);
                addIcon(buttonItem, newBtnLayout);
                buttonHashMap.put(buttonItem, newBtnLayout);
                button = newBtnLayout;
            }
            return button;
        }

        @Override
        public OnClickListener getCloseOnClickListener(ButtonItem buttonItem) {
            final OnClickListener btnOnClickListener = buttonItem.getOnClickListener();
            if (btnOnClickListener==null){
                return onDeleteListener;
            }else{
                return new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnOnClickListener.onClick(v);
                        onDeleteListener.onClick(v);
                    }
                };
            }
        }

        @Override
        public void setOnDeleteListener(OnClickListener onDeleteListener) {
            this.onDeleteListener = onDeleteListener;
        }

        private ClosableRelativeLayout<ButtonItem> getButtonLayout(ButtonItem buttonItem){
            ClosableRelativeLayout newBtnLayout = new ClosableRelativeLayout(getContext());
            LayoutParams newBtnLp = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, BUTTON_LAYOUT_HEIGHT);
            newBtnLp.weight = 1;
            newBtnLayout.setLayoutParams(newBtnLp);
            newBtnLayout.setClosableItem(buttonItem);
            return newBtnLayout;
        }

        private void addIcon(ButtonItem buttonItem, RelativeLayout layout){
            ImageView iconImage = new ImageView(layout.getContext());
            iconImage.setImageDrawable(buttonItem.getIcon());
            RelativeLayout.LayoutParams iconLp = new RelativeLayout.LayoutParams(ICON_SIZE, ICON_SIZE);
            iconLp.addRule(RelativeLayout.CENTER_IN_PARENT);
            iconImage.setLayoutParams(iconLp);
            iconImage.setPadding(ICON_PADDING, ICON_PADDING, ICON_PADDING, ICON_PADDING);
            iconImage.setOnClickListener(getCloseOnClickListener(buttonItem));
            layout.addView(iconImage);
        }
    }
}
