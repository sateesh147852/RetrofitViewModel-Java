package com.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.Hero;
import com.retrofitviewmodel.databinding.PhotoItemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<Hero> retroPhotos;

    public PhotoAdapter(ArrayList<Hero> retroPhotos) {
        this.retroPhotos = retroPhotos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PhotoItemBinding binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Log.i( "onBindViewHolder: ",retroPhotos.get(position).getImageurl());
        holder.binding.setHero(retroPhotos.get(position));
    }

    public void notifyData(ArrayList<Hero> retroPhotos) {
        this.retroPhotos = retroPhotos;
        Log.i("notifyData: ", retroPhotos.size() + "");
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return retroPhotos == null ? 0 : retroPhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        PhotoItemBinding binding;

        public PhotoViewHolder(PhotoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
