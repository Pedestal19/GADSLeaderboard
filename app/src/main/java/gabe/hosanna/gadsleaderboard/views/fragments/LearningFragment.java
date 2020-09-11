package gabe.hosanna.gadsleaderboard.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import gabe.hosanna.gadsleaderboard.R;
import gabe.hosanna.gadsleaderboard.adapters.LearningAdapter;
import gabe.hosanna.gadsleaderboard.models.LearningModel;
import gabe.hosanna.gadsleaderboard.network.retrofit.RetrofitApiCalls;
import gabe.hosanna.gadsleaderboard.network.retrofit.RetrofitClient;

import java.util.List;

import gabe.hosanna.gadsleaderboard.databinding.FragmentLearningLeadersBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {
    LearningAdapter adapter;
    RetrofitApiCalls service;
    private FragmentLearningLeadersBinding binding;

    public LearningFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_learning_leaders, container, false);
        service = RetrofitClient.getRetrofitInstance().create(RetrofitApiCalls.class);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerLayout.startShimmer();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.shimmerLayout.stopShimmer();
    }

    public void initAdapter() {
        Call<List<LearningModel>> call = service.getLearningLeaders();
        call.enqueue(new Callback<List<LearningModel>>() {
            @Override
            public void onResponse(Call<List<LearningModel>> call, Response<List<LearningModel>> response) {
                if (response.isSuccessful()) {
                    List<LearningModel> res = response.body();

                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.rcvLearningLeaders.setVisibility(View.VISIBLE);

                    adapter = new LearningAdapter(getActivity(), res, leader -> {
                        //Do Nothing
                    });
                    binding.rcvLearningLeaders.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                    binding.rcvLearningLeaders.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<LearningModel>> call, Throwable t) {
                binding.shimmerLayout.stopShimmer();
            }
        });
    }
}