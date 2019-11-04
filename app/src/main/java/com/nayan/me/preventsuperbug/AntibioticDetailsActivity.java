package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nayan.me.preventsuperbug.entity.Medicine;

public class AntibioticDetailsActivity extends AppCompatActivity {
    TextView medicineNameTv;
    TextView groupNameTv;
    TextView manufacturerNameTv;
    TextView doseTv;
    TextView strengthTv;
    TextView detailsTv;
    Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antibiotic_details);
        actionbarSetting();
        init();
    }

    private void init() {
        medicine = new Gson().fromJson(getIntent().getStringExtra("medicine"), Medicine.class);
        medicineNameTv = findViewById(R.id.medicine_name_tv);
        groupNameTv = findViewById(R.id.group_name_tv);
        manufacturerNameTv = findViewById(R.id.manufacturer_name_tv);
        doseTv = findViewById(R.id.dose_tv);
        strengthTv = findViewById(R.id.strength_tv);
        detailsTv = findViewById(R.id.details_tv);
        bindData();
    }

    private void bindData() {
        medicineNameTv.setText(medicine.getName());
        if (medicine.getMedicineGroup() != null)
            groupNameTv.setText(medicine.getMedicineGroup().getGroupName());
        if (medicine.getMedicineCompany() != null)
            manufacturerNameTv.setText(medicine.getMedicineCompany().getName());
        doseTv.setText(medicine.getDosageForm());
        strengthTv.setText(medicine.getStrength());

    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("ANTIBIOTIC DETAILS");
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
