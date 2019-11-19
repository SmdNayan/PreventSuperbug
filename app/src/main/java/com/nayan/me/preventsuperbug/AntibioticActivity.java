package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nayan.me.preventsuperbug.adapter.MedicineAdapter;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.Medicine;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class AntibioticActivity extends AppCompatActivity {
    private RecyclerView rcvMedicine;

    private ProgressDialog dialog;
    private FloatingActionButton fab;

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
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PBSBApplication.isLoggedIn() && PBSBApplication.getUser().isAdmin()) {
                    startActivity(new Intent(AntibioticActivity.this, AddMedicineActivity.class));
                }
            }
        });
    }

    private void bindViews() {
        loadingProgressDialog(true, "Medicines", "Loading...");
        HttpRepository getMedicines = new HttpRepository(Config.BASE_URL);
        getMedicines.get("api/v1/medicines", Medicine[].class, new Consumer<Medicine[]>() {
            @Override
            public void accept(Medicine[] medicines) throws Exception {
                bindAdapter(Arrays.asList(medicines));
                loadingProgressDialog(false, "Medicines", "Loading...");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                HttpException exception = (HttpException) throwable;
                if (exception.code() == 403) {
                    Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_LONG).show();
                }
                loadingProgressDialog(false, "Medicines", "Loading...");
            }
        });
    }

    private void bindAdapter(List<Medicine> medicines) {
        MedicineAdapter adapter = new MedicineAdapter(this);
        rcvMedicine.setAdapter(adapter);
        rcvMedicine.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvMedicine.setItemAnimator(new DefaultItemAnimator());
        rcvMedicine.setHasFixedSize(true);
        adapter.setMedicines(medicines);
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Antibiotic list");
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

    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(AntibioticActivity.this);
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
