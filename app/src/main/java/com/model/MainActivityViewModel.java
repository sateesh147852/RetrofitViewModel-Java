package com.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.network.GetDataService;
import com.network.RetrofitClientInstance;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Response<List<Hero>>> retroPhoto;

    public MainActivityViewModel() {
        retroPhoto = new MutableLiveData<>();
    }

    public void makeApiCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Hero>> call = service.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                retroPhoto.postValue(response);
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<Response<List<Hero>>> getMovieListObserver() {
        return retroPhoto;
    }

}
