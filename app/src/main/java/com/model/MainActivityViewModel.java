package com.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.network.GetDataService;
import com.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Response<ResponsePojo>> retroPhoto;

    public MainActivityViewModel() {
        retroPhoto = new MutableLiveData<>();
    }

    public void makeApiCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponsePojo> call = service.getAllCity();

        call.enqueue(new Callback<ResponsePojo>() {
            @Override
            public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                Log.i("onResponse: ",response.body().getData().size()+"");
                retroPhoto.postValue(response);
            }

            @Override
            public void onFailure(Call<ResponsePojo> call, Throwable t) {
                Log.i( "onFailure: ",t.getMessage());
            }
        });

    }

    public MutableLiveData<Response<ResponsePojo>> getMovieListObserver() {
        return retroPhoto;
    }

}
