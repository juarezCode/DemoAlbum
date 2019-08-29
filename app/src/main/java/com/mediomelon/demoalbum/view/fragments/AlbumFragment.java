package com.mediomelon.demoalbum.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        if (args != null) {
            arrayListAlbums = (ArrayList<Album>) args.getSerializable("albums");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        ButterKnife.bind(this, view);

        recyclerViewAlbum.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAlbum.setLayoutManager(linearLayoutManager);

        albumAdapter = new AlbumAdapter(arrayListAlbums, getContext());
        recyclerViewAlbum.setAdapter(albumAdapter);


        return view;
    }

}
