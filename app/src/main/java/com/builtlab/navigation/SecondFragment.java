package com.builtlab.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.builtlab.navigation.databinding.FragmentSecondBinding;
import com.builtlab.viewmodel.SharedViewModel;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Observe data changes
        viewModel.getData().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                binding.textviewSecond.setText("Second Fragment - " + data);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}