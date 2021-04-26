package com.retrofitviewmodel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.adapter.PhotoAdapter;
import com.model.Hero;
import com.model.MainActivityViewModel;
import com.retrofitviewmodel.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeRecyclerView();
        initialize();
    }

    private void initializeRecyclerView() {
        photoAdapter = new PhotoAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvData.setLayoutManager(linearLayoutManager);
        binding.rvData.setAdapter(photoAdapter);

    }

    private void initialize() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getMovieListObserver().observe(this, new Observer<Response<List<Hero>>>() {
            @Override
            public void onChanged(Response<List<Hero>> listResponse) {
                List<Hero> heroList = listResponse.body();
                photoAdapter.notifyData((ArrayList<Hero>) heroList);
            }
        });
        mainActivityViewModel.makeApiCall();
    }
}