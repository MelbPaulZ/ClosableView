package com.developer.paul.closableview;

import android.databinding.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.paul.closableview.databinding.FragmentClosableBtnBinding;

import java.util.HashMap;

/**
 * Created by Paul on 4/5/17.
 */

public class ClosableBtnFragment extends Fragment {

    private FragmentClosableBtnBinding binding;
    private ButtonViewModel btnViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = android.databinding.DataBindingUtil.inflate(inflater, R.layout.fragment_closable_btn, container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HashMap<String, Integer> orderHash = new HashMap<>();
        orderHash.put("Location", 0);
        orderHash.put("Note",1);
        btnViewModel = new ButtonViewModel(orderHash);
        binding.setVm(btnViewModel);
    }
}
