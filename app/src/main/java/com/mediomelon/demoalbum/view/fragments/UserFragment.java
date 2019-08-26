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

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.adapter.UserAdapter;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    @BindView(R.id.recyclerViewUser)
    RecyclerView recyclerViewUser;
    private static final String TAG = "UserFragment";
    ArrayList<User> listUser;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            listUser = (ArrayList<User>) getArguments().getSerializable("listUser");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);

        Log.e(TAG, listUser.toString());

        recyclerViewUser.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(linearLayoutManager);

        UserAdapter userAdapter = new UserAdapter(getContext(), listUser);
        recyclerViewUser.setAdapter(userAdapter);

        return view;
    }

}
