package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.data.DoctorSearch;
import com.nayan.me.preventsuperbug.entity.User;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import io.reactivex.functions.Consumer;

public class DoctorVerificationActivity extends AppCompatActivity {

    MaterialButton cancelBtn;
    MaterialButton submitBtn;
    EditText doctorName;
    EditText speciality;
    EditText hospital;
    EditText qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_verification);
        actionbarSetting();
        init();
        onButtonClick();
    }

    private void init() {
        cancelBtn = findViewById(R.id.cancel_button);
        submitBtn = findViewById(R.id.submit_button);
        doctorName = findViewById(R.id.doctorName);
        speciality = findViewById(R.id.speciality);
        hospital = findViewById(R.id.hospital);
        qualification = findViewById(R.id.qualification);
    }

    private void onButtonClick() {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    private void search() {
        DoctorSearch doctorSearch = new DoctorSearch();
        doctorSearch.setName(doctorName.getText().toString());
        doctorSearch.setHospital(hospital.getText().toString());
        doctorSearch.setQualification(qualification.getText().toString());
        doctorSearch.setSpeciality(speciality.getText().toString());
        if (doctorSearch.getNullFieldName().isEmpty()) {
            doSearch(doctorSearch);
        } else {
            Toast.makeText(getApplicationContext(), doctorSearch.getNullFieldName().toLowerCase(), Toast.LENGTH_LONG).show();
        }
    }

    private void doSearch(DoctorSearch doctorSearch) {
        HttpRepository search = new HttpRepository(Config.BASE_URL);
        search.post("api/v1/search-doctor", doctorSearch, User[].class, new Consumer<User[]>() {

            @Override
            public void accept(User[] users) throws Exception {
                if (users.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (User user : users) {
                        sb.append(user).append("\n");
                    }
                    AlertDialog.Builder ab = new AlertDialog.Builder(DoctorVerificationActivity.this);
                    ab.setTitle("Verification");
                    ab.setMessage(sb);
                    ab.setPositiveButton("Ok", null);
                    ab.create().show();
                }
            }
        }, new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(getApplicationContext(), "No Doctor Found", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Doctor Verification");
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
