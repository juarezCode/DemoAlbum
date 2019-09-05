package com.mediomelon.demoalbum.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.view.activity.PhotosActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.myViewHolder> {
    private PhotosActivity actoresFragment;
    private List<Photo> actoresList;

    public PhotoAdapter(PhotosActivity actoresFragment, List<Photo> actoresList) {
        this.actoresFragment = actoresFragment;
        this.actoresList = actoresList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_photos, viewGroup, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int i) {
        Photo actores = actoresList.get(i);

        Picasso.get().load("" + actores.getUrl())
                .placeholder(R.drawable.ic_download).
                error(R.drawable.girl_1).fit().centerInside()
                .into(holder.imagebanner);

    }

    @Override
    public int getItemCount() {
        return actoresList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imagebanner;

        public myViewHolder(View itemView) {
            super(itemView);
            imagebanner = itemView.findViewById(R.id.imgViewPhotos);
        }
    }
}