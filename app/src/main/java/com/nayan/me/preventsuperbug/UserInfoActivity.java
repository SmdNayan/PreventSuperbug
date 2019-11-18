package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.User;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvAddress, tvContact, tvQualification, tvHospital, tvSpeciality, tvRegistration;
    private LinearLayout llDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        actionbarSetting();
        init();
        bindUserInfo();
    }

    private void bindUserInfo() {
        try {
            User user = PBSBApplication.getUser();
            if (user != null) {
                tvName.setText(user.getFullName() == null ? "" : user.getFullName());
                tvEmail.setText(user.getEmail() == null ? "" : user.getEmail());
                tvAddress.setText(user.getAddress() == null ? "" : user.getAddress());
                tvContact.setText(String.valueOf(user.getContactNumber() == null ? "" : user.getContactNumber()));
                if (user.isDoctor()) {
                    llDoctor.setVisibility(View.VISIBLE);
                    tvHospital.setText(user.getHospital() != null ? user.getHospital() : "");
                    tvQualification.setText(user.getQualification() != null ? user.getQualification() : "");
                    tvRegistration.setText(String.valueOf(user.getRegistrationNumber() != null ? user.getRegistrationNumber() : ""));
                    tvSpeciality.setText(user.getSpeciality() == null ? "" : user.getSpeciality());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        tvContact = findViewById(R.id.tvContact);
        tvQualification = findViewById(R.id.tvQualification);
        tvHospital = findViewById(R.id.tvHospital);
        tvSpeciality = findViewById(R.id.tvSpeciality);
        tvRegistration = findViewById(R.id.tvRegistration);
        llDoctor = findViewById(R.id.llDoctor);
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("USER INFO");
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

    public void onClick(View view) {
        startActivity(new Intent(this, EditProfileActivity.class));
    }
}
