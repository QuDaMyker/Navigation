package com.builtlab.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.lifecycle.ViewModelProvider;

import com.builtlab.navigation.databinding.FragmentFirstBinding;
import com.builtlab.viewmodel.SharedViewModel;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Observe data changes
        viewModel.getData().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                binding.textviewFirst.setText("First Fragment - " + data);
            }
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(v -> {
            viewModel.setData("From First to Second");
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_firstFragment_to_secondFragment);
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}