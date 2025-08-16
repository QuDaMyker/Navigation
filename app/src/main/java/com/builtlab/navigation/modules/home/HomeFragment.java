package com.builtlab.navigation.modules.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.builtlab.navigation.databinding.FragmentHomeBinding;
import com.builtlab.navigation.model.Question;
import com.builtlab.navigation.modules.root.RootActivity;
import com.builtlab.navigation.viewmodel.SharedViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView textView;
    private HomeViewModel homeViewModel;

    private QuestionAdapter questionAdapter;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        questionAdapter = new QuestionAdapter(new QuestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Question question) {
                Toast.makeText(getContext(), question.getQuestionText(), Toast.LENGTH_SHORT).show();
            }
        });

        initViews();
        loadData();
    }

    private void loadData() {
        if (getActivity() instanceof RootActivity) {
            homeViewModel = ((RootActivity) getActivity()).getHomeViewModel();
            homeViewModel.questionsLiveData.observeForever(new Observer<List<Question>>() {
                @Override
                public void onChanged(List<Question> questions) {
                    if (questions != null) {
                        questionAdapter.setQuestions(questions);
                        showRecyclerView();
                    }
                }
            });

            binding.textView.setText(homeViewModel.getCurrentEmail());

            binding.pullToRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    showLoading();
                    homeViewModel.getListQuestions();

                }
            });
        }
    }

    private void initViews() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(questionAdapter);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorTextView.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);
    }

    private void showRecyclerView() {
        binding.pullToRefreshView.setRefreshing(false);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorTextView.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorTextView.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        homeViewModel.questionsLiveData.removeObservers(this);
    }
}