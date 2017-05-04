package com.developer.paul.closableview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import com.developer.paul.closableview.closableItem.RowItem;

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

                addInList("Location", v.getResources().getDrawable(R.drawable.test_male_icon), "Location", onClickListener);

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
                addInList("Repeat", v.getResources().getDrawable(R.drawable.test_female_icon), "Repeat", onClickListener);

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
                        , v.getResources().getDrawable(R.drawable.test_backarrow)
                        , "Note"
                        , onClickListener);
            }
        };
    }

    private boolean containRow(String rowName){
        for (RowItem rowItem: rowItems){
            if (rowItem.getItemName().equals(rowName)){
                return true;
            }
        }
        return false;
    }

    private void addInList(String rowName, Drawable icon, String text, View.OnClickListener onClickListener){
        RowItem rowItem = new RowItem();
        rowItem.setItemName(rowName);
        rowItem.setIcon(icon);
        rowItem.setText(text);
        rowItem.setClickListener(onClickListener);
        rowItems.add(rowItem);
        notifyPropertyChanged(BR.rowItems);
    }

}
