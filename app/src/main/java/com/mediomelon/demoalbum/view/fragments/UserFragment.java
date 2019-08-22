package com.mediomelon.demoalbum.view.fragments;


import android.os.Bundle;
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
import com.mediomelon.demoalbum.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    @BindView(R.id.recyclerViewUser)
    RecyclerView recyclerViewUser;
    private static final String TAG = "UserFragment";
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);

        MainActivity activity = (MainActivity) getActivity();
        Log.e(TAG, activity.getListUser().toString());
        ArrayList<User> listUser = activity.getListUser();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(linearLayoutManager);

        UserAdapter userAdapter = new UserAdapter(getContext(), listUser);
        recyclerViewUser.setAdapter(userAdapter);

        return view;
    }

}
