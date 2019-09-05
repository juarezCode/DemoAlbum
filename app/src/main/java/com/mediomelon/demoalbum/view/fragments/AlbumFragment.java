package com.mediomelon.demoalbum.view.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.adapter.AlbumAdapter;
import com.mediomelon.demoalbum.model.entity.Album;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {

    private static final String TAG = "AlbumFragment";
    private ArrayList<Album> arrayListAlbums;
    private Bundle args;
    private AlbumAdapter albumAdapter;

    @BindView(R.id.recyclerViewAlbums)
    RecyclerView recyclerViewAlbum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if(args != null){
            arrayListAlbums = (ArrayList<Album>) args.getSerializable("albums");
            for(int i = 0;i<arrayListAlbums.size();i++){
                Log.e(TAG,"ArrayList datos: " + arrayListAlbums.get(i).getTitle());
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album,container,false);
        ButterKnife.bind(this,view);

        recyclerViewAlbum.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAlbum.setLayoutManager(linearLayoutManager);

        albumAdapter = new AlbumAdapter(arrayListAlbums,getContext());
        recyclerViewAlbum.setAdapter(albumAdapter);


        return view;
    }

}
