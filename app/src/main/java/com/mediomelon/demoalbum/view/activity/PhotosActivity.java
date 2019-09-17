package com.mediomelon.demoalbum.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.adapter.PhotoAdapter;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.presenter.PhotoPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosActivity extends AppCompatActivity implements IPhotos.IView {

    private IPhotos.IPresenter photosPresenter;
    private final static String TAG = "PhotosActivity";

    @BindView(R.id.recyclerViewPhotos)
    RecyclerView recyclerViewPhoto;
    @BindView(R.id.progressBarPhotos)
    ProgressBar progressBar;
    @BindView(R.id.textViewtitle)
    TextView textViewtitle;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.title_toolbar)
    TextView titleToolbar;
    @BindView(R.id.imgNetworkError)
    ImageView imgNetworkError;
    @BindView(R.id.txtNetworkError)
    TextView txtNetworkError;
    @BindView(R.id.btnNetworkRetry)
    Button btnNetworkRetry;

    String title;

    ArrayList<Photo> listPhoto;
    PhotoAdapter photoAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        ButterKnife.bind(this);
        photosPresenter = new PhotoPresenter(this, getApplicationContext());

        btnBack.setVisibility(View.VISIBLE);
        titleToolbar.setText("Fotos");

        Bundle bundle = getIntent().getExtras();

        int id = bundle.getInt("idPhoto");
        title = bundle.getString("title");

        getPhotos(id);

        btnBack.setOnClickListener(v -> {
          /*FLAG_ACTIVITY_SINGLE_TOP: si la actividad que se lanza con el intent
                   ya esta en la pila de actividades, en lugar de lanzar una nueva instancia
                   de dicha actividad, el resto de actividades en la pila seran cerradas y se resolvera
                   el intent por la actividad a la que se llamo;*/
            startActivity(new Intent(getBaseContext(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        });

        btnNetworkRetry.setOnClickListener(v -> {
            imgNetworkError.setVisibility(View.GONE);
            txtNetworkError.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            btnNetworkRetry.setVisibility(View.GONE);
            getPhotos(id);
        });

    }

    @Override
    public void getPhotos(int albumId) {
        photosPresenter.getPhotos(albumId);
    }

    @Override
    public void showPhotos(ArrayList<Photo> photos) {
        progressBar.setVisibility(View.GONE);
        textViewtitle.setText(title);
        listPhoto = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, listPhoto);

        layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 3);

        recyclerViewPhoto.setLayoutManager(layoutManager);
        recyclerViewPhoto.setAdapter(photoAdapter);

        listPhoto.addAll(photos);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorInternetConnection() {
        //Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        imgNetworkError.setVisibility(View.VISIBLE);
        txtNetworkError.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        btnNetworkRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPhotos(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        Log.e(TAG, "Error: " + error);
    }
}
