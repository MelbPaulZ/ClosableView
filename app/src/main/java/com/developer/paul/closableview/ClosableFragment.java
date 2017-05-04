package com.developer.paul.closableview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.paul.closableview.databinding.FragmentClosableBinding;

import java.util.HashMap;

/**
 * Created by Paul on 3/5/17.
 */

public class ClosableFragment extends Fragment {

    private FragmentClosableBinding binding;
    private ViewModel viewModel;
    private ButtonViewModel buttonViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_closable, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HashMap<String, Integer> orderHash = new HashMap<>();
        orderHash.put("Location", 0);
        orderHash.put("Repeat", 1);
        orderHash.put("Note",2);
        viewModel = new ViewModel(orderHash);
        buttonViewModel = new ButtonViewModel(orderHash);
        binding.setItem(viewModel);
        binding.setVm(buttonViewModel);
    }
}
