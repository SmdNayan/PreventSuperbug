package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.nayan.me.preventsuperbug.adapter.MedicineAdapter;

public class AntibioticActivity extends AppCompatActivity {
    private RecyclerView rcvMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antibiotic);
        init();
        bindViews();
        actionbarSetting();
    }

    private void init() {
        rcvMedicine = findViewById(R.id.rcvMedicine);
    }

    private void bindViews() {
        MedicineAdapter adapter = new MedicineAdapter();
        rcvMedicine.setAdapter(adapter);
        rcvMedicine.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvMedicine.setItemAnimator(new DefaultItemAnimator());
        rcvMedicine.setHasFixedSize(true);
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
