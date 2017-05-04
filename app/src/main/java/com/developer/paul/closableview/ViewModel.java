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
    private ObservableList<RowItem> rowItems = new ObservableArrayList<>();
    private HashMap<String, Integer> orderHash;

    public ViewModel(HashMap<String, Integer> orderHash) {
        this.orderHash = orderHash;
    }

    @Bindable
    public ObservableList<RowItem> getRowItems() {
        return rowItems;
    }

    public void setRowItems(ObservableList<RowItem> rowItems) {
        this.rowItems = rowItems;
        notifyPropertyChanged(BR.rowItems);
    }

    @Bindable
    public HashMap<String, Integer> getOrderHash() {
        return orderHash;
    }

    public View.OnClickListener addLocation(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (containRow("Location")){
                    return;
                }
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Location", Toast.LENGTH_SHORT).show();
                    }
                };

                addInList("Location", v.getResources().getDrawable(R.drawable.contact_male_icon), "Location", onClickListener, findPosition("Location"));

            }
        };
    }

    public View.OnClickListener addRepeat(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (containRow("Repeat")){
                    return;
                }
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Repeat", Toast.LENGTH_SHORT).show();
                    }
                };
                addInList("Repeat", v.getResources().getDrawable(R.drawable.contact_female_icon), "Repeat", onClickListener, findPosition("Repeat"));

            }
        };
    }

    public View.OnClickListener addNote(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (containRow("Note")){
                    return;
                }
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Note", Toast.LENGTH_SHORT).show();
                    }
                };
                addInList("Note"
                        , v.getResources().getDrawable(R.drawable.icon_bg_backarrow)
                        , "Note"
                        , onClickListener
                        , findPosition("Note"));
            }
        };
    }

    private boolean containRow(String rowName){
        for (RowItem rowItem: rowItems){
            if (rowItem.getRowName().equals(rowName)){
                return true;
            }
        }
        return false;
    }

    private void addInList(String rowName, Drawable icon, String text, View.OnClickListener onClickListener, int position){
        RowItem rowItem = new RowItem();
        rowItem.setRowName(rowName);
        rowItem.setIcon(icon);
        rowItem.setText(text);
        rowItem.setClickListener(onClickListener);
        rowItems.add(position, rowItem);
        notifyPropertyChanged(BR.rowItems);
    }

    private int findPosition(String rowName){
        int len = rowItems.size();
        int rowNumIndex = orderHash.get(rowName);
        for (int i = 0 ; i < len ; i++){
            int curViewIndex = orderHash.get(rowItems.get(i).getRowName());
            if (curViewIndex>=rowNumIndex){
                return i;
            }
        }
        return len;
    }
}
