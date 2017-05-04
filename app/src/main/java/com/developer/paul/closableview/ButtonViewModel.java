package com.developer.paul.closableview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.Toast;

import com.developer.paul.closableview.closableItem.ButtonItem;

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


    @Bindable
    public HashMap<String, Integer> getOrderHashMap() {
        return orderHashMap;
    }


    public boolean isContainBtn(String buttonName){
        for (ButtonItem buttonItem: buttonItems){
            if (buttonItem.getItemName().equals(buttonName)){
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
                btnItem.setItemName("Location");
                btnItem.setIcon(v.getResources().getDrawable(R.drawable.test_female_icon));
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

    public View.OnClickListener addRepeat(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContainBtn("Repeat")){
                    return;
                }
                ButtonItem btnItem = new ButtonItem();
                btnItem.setItemName("Repeat");
                btnItem.setIcon(v.getResources().getDrawable(R.drawable.test_male_icon));
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Repeat", Toast.LENGTH_SHORT).show();
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
                btnItem.setItemName("Note");
                btnItem.setIcon(v.getResources().getDrawable(R.drawable.test_backarrow));
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
