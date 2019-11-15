package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.entity.Complain;
import com.nayan.me.preventsuperbug.network.http.CiHttpClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ComplainDetailsActivity extends AppCompatActivity  {
    TextView providerName;
    TextView farmacyName;
    TextView address;
    TextView complainNo;
    TextView tvDescription;
    ImageView ivComplainImage;
    private Complain complain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_details);
        complain = CiHttpClient.getGson().fromJson(getIntent().getStringExtra("complain"), Complain.class);
        init();
        bindData();
    }

    private void bindData() {
        if (complain != null) {
            if (complain.getComplainId() != null) {
                complainNo.setText("Complain No: #" + complain.getComplainId());
            }
            if (complain.getProviderName() != null) {
                providerName.setText("Provider Name: " + complain.getProviderName());
            }
            if (complain.getFarmacyName() != null) {
                farmacyName.setText("Farmacy Name: " + complain.getFarmacyName());
            }
            if (complain.getAddress() != null) {
                address.setText("Address: " + complain.getAddress());
            }
            if (complain.getDetails() != null) {
                tvDescription.setText(complain.getDetails());
            }
            if (complain.getPrescriptionImage() != null && !complain.getPrescriptionImage().isEmpty()) {
                Picasso.with(this).load(Config.BASE_URL + "api/v1/downloadFile/" + complain.getPrescriptionImage()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        String e = "";
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        String e = "";
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        String e = "";
                    }
                });
//                Picasso.with(this).load(Config.BASE_URL + "api/v1/downloadFile/" + complain.getPrescriptionImage()).into(ivComplainImage, this);
            }
        }
    }

    private void init() {
        providerName = findViewById(R.id.providerName);
        farmacyName = findViewById(R.id.farmacyName);
        address = findViewById(R.id.address);
        complainNo = findViewById(R.id.complainNo);
        tvDescription = findViewById(R.id.tvDescription);
        ivComplainImage = findViewById(R.id.ivComplainImage);
        actionbarSetting();
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.complainDetToolbar));
        getSupportActionBar().setTitle("Complain");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


}
