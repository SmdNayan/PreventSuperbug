package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.Medicine;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import java.util.Arrays;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class AntibioticDetailsActivity extends AppCompatActivity {
    TextView medicineNameTv;
    TextView groupNameTv;
    TextView manufacturerNameTv;
    TextView doseTv;
    TextView strengthTv;
    TextView detailsTv;
    int medicineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antibiotic_details);
        actionbarSetting();
        init();
    }

    private void init() {
        medicineId = getIntent().getIntExtra("medicine", -1);
        medicineNameTv = findViewById(R.id.medicine_name_tv);
        groupNameTv = findViewById(R.id.group_name_tv);
        manufacturerNameTv = findViewById(R.id.manufacturer_name_tv);
        doseTv = findViewById(R.id.dose_tv);
        strengthTv = findViewById(R.id.strength_tv);
        detailsTv = findViewById(R.id.details_tv);
        if (medicineId != -1) {
            getMedicine();
        }
    }

    private void getMedicine() {
        loadingProgressDialog(true, "Medicine", "Loading...");
        HttpRepository getMedicine = new HttpRepository(Config.BASE_URL);
        getMedicine.get("api/v1/medicines/" + medicineId, Medicine.class, new Consumer<Medicine>() {
            @Override
            public void accept(Medicine medicine) throws Exception {
                if (medicine != null)
                    bindData(medicine);
                loadingProgressDialog(false, "Medicine", "Loading your medicine...");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                HttpException exception = (HttpException) throwable;
                if (exception.code() == 403) {
                    Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You Have No Medicine!", Toast.LENGTH_LONG).show();
                }
                loadingProgressDialog(false, "Medicine", "Loading...");
            }
        });
    }

    private void bindData(Medicine medicine) {
        medicineNameTv.setText(medicine.getName());
        if (medicine.getMedicineGroup() != null)
            groupNameTv.append(medicine.getMedicineGroup().getGroupName());
        if (medicine.getMedicineCompany() != null)
            manufacturerNameTv.append(medicine.getMedicineCompany().getName());
        doseTv.append(medicine.getDosageForm());
        strengthTv.append(medicine.getStrength());

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

    private ProgressDialog dialog;

    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(AntibioticDetailsActivity.this);
                    dialog.setCancelable(false);
                    dialog.setProgressStyle(android.R.attr.progressBarStyleLarge);
                    // dialog.setIndeterminateDrawable(BaseActivity.this.getResources().getDrawable(R.drawable.dialog_cirlce));

                }
                if (visibility) {
//                dialog.setTitle(title == null ? getString(R.string.jatri_sheba) : title);
                    dialog.setTitle(title == null ? "" : title);
                    if (msg != null)
                        dialog.setMessage(msg);
                    dialog.show();
                } else if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }
}
