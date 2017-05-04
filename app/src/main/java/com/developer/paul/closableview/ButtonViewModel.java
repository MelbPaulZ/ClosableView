package com.developer.paul.closableview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Paul on 4/5/17.
 */

public class ButtonViewModel extends BaseObservable {
    private HashMap<String, Integer> orderHashMap;
    private ObservableList<ButtonItem> buttonItems = new ObservableArrayList<>();

    public ButtonViewModel(HashMap<String, Integer> orderHashMap) {
        this.orderHashMap = orderHashMap;
    }

    @Bindable
    public ObservableList<ButtonItem> getButtonItems() {
        return buttonItems;
    }

    public void setButtonItems(ObservableList<ButtonItem> buttonItems) {
        this.buttonItems = buttonItems;
        notifyPropertyChanged(BR.buttonItems);
    }

    @Bindable
    public HashMap<String, Integer> getOrderHashMap() {
        return orderHashMap;
    }

    public void setOrderHashMap(HashMap<String, Integer> orderHashMap) {
        this.orderHashMap = orderHashMap;
        notifyPropertyChanged(BR.orderHashMap);
    }

    public boolean isContainBtn(String buttonName){
        for (ButtonItem buttonItem: buttonItems){
            if (buttonItem.getButtonName().equals(buttonName)){
                return true;
            }
        }
        return false;
    }

    public View.OnClickListener addLocation(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContainBtn("Location")){
                    return;
                }
                ButtonItem btnItem = new ButtonItem();
                btnItem.setButtonName("Location");
                btnItem.setIcon(v.getResources().getDrawable(R.drawable.contact_female_icon));
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Location", Toast.LENGTH_SHORT).show();
                    }
                };
                btnItem.setOnClickListener(onClickListener);
                buttonItems.add(btnItem);
            }
        };
    }

    public View.OnClickListener addNote(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContainBtn("Note")){
                    return;
                }
                ButtonItem btnItem = new ButtonItem();
                btnItem.setButtonName("Note");
                btnItem.setIcon(v.getResources().getDrawable(R.drawable.contact_male_icon));
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Note", Toast.LENGTH_SHORT).show();
                    }
                };
                btnItem.setOnClickListener(onClickListener);
                buttonItems.add(btnItem);
            }
        };
    }
}
