package com.mediomelon.demoalbum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.model.entity.Album;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private List<Album> albums;
    private Context ctx;
    private int count;

    public AlbumAdapter(List<Album> albums, Context ctx){
        this.albums = albums;
        this.ctx = ctx;
        count = 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.cardview_album,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
        Album album = albums.get(i);
        count++;
        vh.txtNumberAlbum.setText(album.getId() + ".");
        vh.txtNameAlbum.setText(album.getTitle());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number_album)
        TextView txtNumberAlbum;
        @BindView(R.id.name_album)
        TextView txtNameAlbum;

        public ViewHolder(@NonNull View v) {
            super(v);
            ButterKnife.bind(this,v);

        }
    }
}
