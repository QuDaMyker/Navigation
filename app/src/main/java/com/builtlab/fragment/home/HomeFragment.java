package com.builtlab.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.builtlab.model.User;
import com.builtlab.navigation.MainActivity;
import com.builtlab.navigation.databinding.FragmentHomeBinding;
import com.builtlab.viewmodel.SharedViewModel;

public class HomeFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView textView;
    private HomeViewModel homeViewModel;

    private UserAdapter userAdapter;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        textView = binding.textView;

        homeViewModel = ((MainActivity) getActivity()).getHomeViewModel();

        userAdapter = new UserAdapter(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Toast.makeText(getActivity(), user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(userAdapter);


        homeViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            userAdapter.setUserList(users);
        });

        // Observe data changes
        viewModel.getData().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                textView.setText("Home Fragment - Data: " + data);
            } else {
                textView.setText("Home Fragment");
            }
        });

        initViews();

        return binding.getRoot();


    }

    private void initViews() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorTextView.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);
    }

    private void showRecyclerView() {
        binding.progressBar.setVisibility(View.GONE);
        binding.errorTextView.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }
}