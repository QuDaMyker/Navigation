package com.builtlab.navigation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.builtlab.viewmodel.SharedViewModel;

public class OptionsFragment extends Fragment {

    private SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Button buttonToFirst = view.findViewById(R.id.buttonToFirst);
        buttonToFirst.setOnClickListener(v -> {
            viewModel.setData("From Options");
            NavHostFragment.findNavController(OptionsFragment.this)
                    .navigate(R.id.action_optionsFragment_to_firstFragment);
        });

        return view;
    }
}