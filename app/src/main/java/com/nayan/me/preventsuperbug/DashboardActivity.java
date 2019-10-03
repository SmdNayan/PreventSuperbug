package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {

    MaterialCardView antibioticCV;
    MaterialCardView complainCV;
    MaterialCardView articleCV;
    MaterialCardView mentorshipCV;
    MaterialCardView superbugCV;
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
        superbugCV = findViewById(R.id.superbug_cv);
        aboutCV = findViewById(R.id.about_cv);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
    }
}
