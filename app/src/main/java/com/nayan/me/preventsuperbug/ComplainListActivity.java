package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.nayan.me.preventsuperbug.adapter.ArticleAdapter;
import com.nayan.me.preventsuperbug.adapter.ComplainAdapter;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.Complain;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class ComplainListActivity extends AppCompatActivity {
    private RecyclerView rcvComplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list);
        rcvComplain=findViewById(R.id.rcvComplain);
        actionbarSetting();
        getComplains();
    }

    private void getComplains() {
        loadingProgressDialog(true, "Complains", "Loading...");
        HttpRepository getMedicines = new HttpRepository(Config.BASE_URL);
        getMedicines.get("api/v1/complains", Complain[].class, new Consumer<Complain[]>() {
            @Override
            public void accept(Complain[] complains) throws Exception {
                if (complains != null)
                    bindData(Arrays.asList(complains));
                loadingProgressDialog(false, "Complain", "Loading...");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                HttpException exception = (HttpException) throwable;
                if (exception.code() == 403) {
                    Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_LONG).show();
                }
                loadingProgressDialog(false, "Complain", "Loading...");
            }
        });
    }

    private void bindData(List<Complain> complains) {
        ComplainAdapter adapter = new ComplainAdapter(this);
        rcvComplain.setAdapter(adapter);
        rcvComplain.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvComplain.setItemAnimator(new DefaultItemAnimator());
        rcvComplain.setHasFixedSize(true);
        adapter.setComplains(complains);
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.complainToolbar));
        getSupportActionBar().setTitle("Complains");
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
                    dialog = new ProgressDialog(ComplainListActivity.this);
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
