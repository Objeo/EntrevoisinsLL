package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    @BindView(R.id.detail_avatar)
    ImageView detailAvatar;
    @BindView(R.id.favorite_button)
    FloatingActionButton fab;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_address)
    TextView address;
    @BindView(R.id.detail_phone_number)
    TextView phoneNumber;
    @BindView(R.id.detail_facebook)
    TextView facebookProfile;
    @BindView(R.id.detail_am_title)
    TextView aboutMeTitle;
    @BindView(R.id.detail_am_text)
    TextView abouteMeText;
    @BindView(R.id.back_button)
    FloatingActionButton backButton;
    @BindView(R.id.detail_title)
    TextView title;
    private Neighbour mNeighbour;
    private NeighbourApiService apiService = DI.getNeighbourApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setIcon(getDrawable(R.drawable.ic_star_white_24dp));
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);


        long id = (long) getIntent().getSerializableExtra("NEIGHBOUR");
        mNeighbour = apiService.getNeighbourById(id);
        //mNeighbour = (Neighbour) getIntent().getSerializableExtra("NEIGHBOUR");
        refreshFavoriteColor();


        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .into(this.detailAvatar);


        this.name.setText(mNeighbour.getName());
        this.title.setText(mNeighbour.getName());
        this.address.setText(mNeighbour.getAddress());
        this.phoneNumber.setText(mNeighbour.getPhoneNumber());
        this.facebookProfile.setText("www.facebook.fr/" + mNeighbour.getName());
        this.aboutMeTitle.setText("A propos de moi");
        this.abouteMeText.setText(mNeighbour.getAboutMe());
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Log.d(TAG, "onCreate: " + mNeighbour.getName() + " " + mNeighbour.isFavorite());

    }

    @OnClick(R.id.favorite_button)
    void favorite() {
        mNeighbour.toggleFavoriteStatus();
        refreshFavoriteColor();

    }


    private void refreshFavoriteColor() {
        int color;
        if (mNeighbour.isFavorite()) {
            Log.d(TAG, "refreshFavoriteColor: " + mNeighbour.getName() + " " + mNeighbour.isFavorite());
            color = ContextCompat.getColor(getApplicationContext(), R.color.colorFavorite);
        } else {
            color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
            Log.d(TAG, "refreshFavoriteColor: " + mNeighbour.getName() + " " + mNeighbour.isFavorite());
        }
        fab.setColorFilter(color);
    }


    @OnClick
    void showDetail() {
        //ProfileActivity.navigate(this);
    }
}