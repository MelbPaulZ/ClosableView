package com.developer.paul.closableview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Paul on 3/5/17.
 */

public class ViewModel extends BaseObservable {
    private ObservableList<String> rowNames = new ObservableArrayList<>();
    private ObservableList<Drawable> icons = new ObservableArrayList<>();
    private ObservableList<String> texts = new ObservableArrayList<>();
    private ObservableList<View.OnClickListener> onClickListeners = new ObservableArrayList<>();;
    private HashMap<String, Integer> orderHash;

    public ViewModel(HashMap<String, Integer> orderHash) {
        this.orderHash = orderHash;
    }

    @Bindable
    public ObservableList<String> getRowNames() {
        return rowNames;
    }

    public void setRowNames(ObservableList<String> rowNames) {
        this.rowNames = rowNames;
        notifyPropertyChanged(BR.rowNames);
    }

    @Bindable
    public ObservableList<Drawable> getIcons() {
        return icons;
    }

    public void setIcons(ObservableList<Drawable> icons) {
        this.icons = icons;
        notifyPropertyChanged(BR.icons);
    }

    @Bindable
    public ObservableList<String> getTexts() {
        return texts;
    }

    public void setTexts(ObservableList<String> texts) {
        this.texts = texts;
        notifyPropertyChanged(BR.texts);
    }

    @Bindable
    public ObservableList<View.OnClickListener> getOnClickListeners() {
        return onClickListeners;
    }

    public void setOnClickListeners(ObservableList<View.OnClickListener> onClickListeners) {
        this.onClickListeners = onClickListeners;
        notifyPropertyChanged(BR.onClickListeners);
    }

    @Bindable
    public HashMap<String, Integer> getOrderHash() {
        return orderHash;
    }

    public View.OnClickListener addLocation(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowNames.contains("Location")){
                    return;
                }
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Location", Toast.LENGTH_SHORT).show();
                    }
                };
                addInList(v, "Location"
                        , v.getResources().getDrawable(R.drawable.contact_male_icon)
                        , "Location"
                        ,onClickListener
                        , findPosition("Location"));

            }
        };
    }

    public View.OnClickListener addRepeat(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (rowNames.contains("Repeat")){
                    return;
                }
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Repeat", Toast.LENGTH_SHORT).show();
                    }
                };
                addInList(v ,"Repeat"
                        , v.getResources().getDrawable(R.drawable.contact_female_icon)
                        , "Repeat"
                        , onClickListener
                        , findPosition("Repeat"));

            }
        };
    }

    public View.OnClickListener addNote(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowNames.contains("Note")){
                    return;
                }
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Note", Toast.LENGTH_SHORT).show();
                    }
                };
                addInList(v, "Note"
                        , v.getResources().getDrawable(R.drawable.icon_bg_backarrow)
                        , "Note"
                        , onClickListener
                        , findPosition("Note"));
            }
        };
    }

    private void addInList(
            View v
            ,String rowName
            ,Drawable icon
            ,String text
            ,View.OnClickListener onClickListener
            ,int position){
        rowNames.add(position, rowName);
        icons.add(position, icon);
        texts.add(position, text);
        onClickListeners.add(position, onClickListener);
        notifyPropertyChanged(BR.rowNames);
        notifyPropertyChanged(BR.icons);
        notifyPropertyChanged(BR.texts);
        notifyPropertyChanged(BR.onClickListeners);
    }

    private int findPosition(String rowName){
        int len = rowNames.size();
        int rowNumIndex = orderHash.get(rowName);
        for (int i = 0 ; i < len ; i++){
            int curViewIndex = orderHash.get(rowNames.get(i));
            if (curViewIndex>=rowNumIndex){
                return i;
            }
        }
        return len;
    }
}
