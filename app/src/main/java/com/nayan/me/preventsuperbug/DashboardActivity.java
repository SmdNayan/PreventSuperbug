package com.nayan.me.preventsuperbug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.Role;
import com.nayan.me.preventsuperbug.entity.User;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    MaterialCardView antibioticCV;
    MaterialCardView complainCV;
    MaterialCardView articleCV;
    MaterialCardView mentorshipCV;
    MaterialCardView dctrVerification;
    MaterialCardView superbugCV;
    MaterialCardView superbugNewsCv;
    BottomNavigationView btmMenu;
    MaterialCardView userCV;

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
        superbugNewsCv = findViewById(R.id.superbug_news_cv);
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
                if (PBSBApplication.isLoggedIn()) {
                    User user = PBSBApplication.getUser();
                    if (user != null && user.isAdmin()) {
                        startActivity(new Intent(DashboardActivity.this, ComplainListActivity.class));
                    } else {
                        startActivity(new Intent(DashboardActivity.this, ComplainActivity.class));
                    }
                }
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
        superbugNewsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, SuperbugNewsActivity.class));
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
                if (PBSBApplication.isLoggedIn())
                    startActivity(new Intent(DashboardActivity.this, UserInfoActivity.class));
                else {
                    Toast.makeText(getApplicationContext(), "Please login first!", Toast.LENGTH_LONG).show();
                }
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
