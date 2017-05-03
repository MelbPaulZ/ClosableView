package com.developer.paul.closableview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.paul.closableview.databinding.FragmentClosableBinding;

/**
 * Created by Paul on 3/5/17.
 */

public class ClosableFragment extends Fragment {
//
//    private FragmentClosableBinding binding;
//    private ItemViewModel viewModel;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_closable, container,false);
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        viewModel = new ItemViewModel();
//        binding.setItem(viewModel);
//
//        TextView tv = (TextView) getActivity().findViewById(R.id.t1);
//        LinearLayout ly = (LinearLayout) getActivity().findViewById(R.id.fragment_linearlayout);
//
//        viewModel.setTextView(tv);
//        viewModel.setLinearLayout(ly);
//
//    }
}
