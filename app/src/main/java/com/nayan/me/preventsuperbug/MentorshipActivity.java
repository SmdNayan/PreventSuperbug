package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nayan.me.preventsuperbug.adapter.MentorsDoctorAd;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MentorshipActivity extends AppCompatActivity {

    Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentorship);
        res = getResources();
        actionbarSetting();
        getDoctorData();
    }

    GridView dctrGridView;
    private void doctorSet(){
        MentorsDoctorAd mentorsDoctorAd = new MentorsDoctorAd(MentorshipActivity.this, dctrName, dctrImage, dctrSpe);
        dctrGridView = findViewById(R.id.dctr_gv);
        dctrGridView.setAdapter(mentorsDoctorAd);

        dctrGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if (position >= 0) {

                    intent = new Intent(MentorshipActivity.this, ArticleActivity.class);
                    Bundle b = new Bundle();
                    b.putString("DCTR_ID", dctrName.get(position));
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }

    List<String> dctrName = new ArrayList<>();
    List<String> dctrSpe = new ArrayList<>();
    List<Bitmap> dctrImage = new ArrayList<>();
    String[] name = {"Dr. Abu Reza", "Dr. Afsana Begum", "Dr. Anup Kumar Saha", "Dr. Abdullah Al Mamun", "Dr. Md. Abul Kalam Azad", "Professor Dr. A.B.M Abullah"};
    String[] spe = {"Internal Medicine", "Internal Medicine", "Medicine", "Internal Medicine", "Medicine", "Medicine"};
    private void getDoctorData(){
        Drawable[] catCImg = {res.getDrawable(R.drawable.dr_manjur_ahmed_sajib), res.getDrawable(R.drawable.priyanka_rani), res.getDrawable(R.drawable.purush_dctr),
                res.getDrawable(R.drawable.dr_manjur_ahmed_sajib), res.getDrawable(R.drawable.priyanka_rani), res.getDrawable(R.drawable.purush_dctr)};
        for(int i=0; i<=5; i++){
            for(int j=0; j<6; j++){
                dctrName.add(name[j]);
                dctrSpe.add(spe[j]);
                dctrImage.add(getImage(getBytesFromDrawable(catCImg[j])));
            }
        }
        doctorSet();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public byte[] getBytesFromDrawable(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("MENTORSHIP");
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
