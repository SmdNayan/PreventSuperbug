package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Dashboard extends AppCompatActivity {

    MaterialCardView antibioticCV;
    MaterialCardView complainCV;
    MaterialCardView articleCV;
    MaterialCardView mentorshipCV;
    MaterialCardView superbugCV;
    MaterialCardView aboutCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        initialize();
        onButtonClick();

    }

    private void initialize(){
        antibioticCV = findViewById(R.id.antibiotic_cv);
        complainCV = findViewById(R.id.complain_cv);
        articleCV = findViewById(R.id.article_cv);
        mentorshipCV = findViewById(R.id.mentorship_cv);
        superbugCV = findViewById(R.id.superbug_cv);
        aboutCV = findViewById(R.id.about_cv);
    }

    private void onButtonClick(){
        antibioticCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Antibiotic.class));
            }
        });
        complainCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Complain.class));
            }
        });
        articleCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Article.class));
            }
        });
        mentorshipCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Mentorship.class));
            }
        });
        superbugCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Superbug.class));
            }
        });
        aboutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, About.class));
            }
        });
    }
}
