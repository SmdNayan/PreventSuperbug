package com.nayan.me.preventsuperbug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.nayan.me.preventsuperbug.core.PBSBApplication;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    MaterialCardView antibioticCV;
    MaterialCardView complainCV;
    MaterialCardView articleCV;
    MaterialCardView mentorshipCV;
    MaterialCardView dctrVerification;
    MaterialCardView superbugCV;
    BottomNavigationView btmMenu;
    MaterialCardView userCV;
    MaterialCardView aboutCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
        onButtonClick();
    }

    private void initialize() {
        antibioticCV = findViewById(R.id.antibiotic_cv);
        complainCV = findViewById(R.id.complain_cv);
        articleCV = findViewById(R.id.article_cv);
        mentorshipCV = findViewById(R.id.mentorship_cv);
        dctrVerification = findViewById(R.id.dctr_v_cv);
        superbugCV = findViewById(R.id.superbug_cv);
        userCV = findViewById(R.id.user_profile);
        aboutCV = findViewById(R.id.about_cv);
        btmMenu = findViewById(R.id.btmMenu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btmMenu.setOnNavigationItemSelectedListener(this);
    }

    private void onButtonClick() {
        antibioticCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AntibioticActivity.class));
            }
        });
        complainCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ComplainActivity.class));
            }
        });
        articleCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ArticleActivity.class));
            }
        });
        mentorshipCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MentorshipActivity.class));
            }
        });
        superbugCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, SuperbugActivity.class));
            }
        });
        aboutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AboutActivity.class));
            }
        });
        dctrVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, DoctorVerificationActivity.class));
            }
        });
        userCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, UserInfoActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PBSBApplication.isLoggedIn()) {
            btmMenu.getMenu().findItem(R.id.mnLogout).setVisible(true);
            btmMenu.getMenu().findItem(R.id.mnLogin).setVisible(false);
            btmMenu.getMenu().findItem(R.id.mnSignup).setVisible(false);
        } else {
            btmMenu.getMenu().findItem(R.id.mnLogin).setVisible(true);
            btmMenu.getMenu().findItem(R.id.mnSignup).setVisible(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnLogin) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (item.getItemId() == R.id.mnSignup) {
            startActivity(new Intent(this, SignupActivity.class));
        } else if (item.getItemId() == R.id.mnLogout) {
            PBSBApplication.setToken(null);
            startActivity(getIntent());
        }
        return true;
    }
}
