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

import com.nayan.me.preventsuperbug.adapter.ArticleAdapter;
import com.nayan.me.preventsuperbug.adapter.NewsAdapter;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.SuperbugNews;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class SuperbugNewsActivity extends AppCompatActivity {

    private RecyclerView rcvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superbug_news);
        actionbarSetting();
        init();
        getNews();
    }
    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("SuperBug News");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void getNews() {
        loadingProgressDialog(true, "Superbug news", "Loading...");
        HttpRepository getMedicines = new HttpRepository(Config.BASE_URL);
        getMedicines.get("api/v1/superbug-news", SuperbugNews[].class, new Consumer<SuperbugNews[]>() {
            @Override
            public void accept(SuperbugNews[] superbugNews) throws Exception {
                if (superbugNews != null)
                    bindData(Arrays.asList(superbugNews));
                loadingProgressDialog(false, "Superbug News", "Loading...");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                HttpException exception = (HttpException) throwable;
                if (exception.code() == 403) {
                    Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_LONG).show();
                }
                loadingProgressDialog(false, "Superbug News", "Loading...");
            }
        });
    }

    private void bindData(List<SuperbugNews> dataList) {
        NewsAdapter adapter = new NewsAdapter(this);
        rcvNews.setAdapter(adapter);
        rcvNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvNews.setItemAnimator(new DefaultItemAnimator());
        rcvNews.setHasFixedSize(true);
        adapter.setNews(dataList);
    }

    private void init() {
        rcvNews = findViewById(R.id.rcvNews);
    }

    private ProgressDialog dialog;

    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(SuperbugNewsActivity.this);
                    dialog.setCancelable(false);
                    dialog.setProgressStyle(android.R.attr.progressBarStyleLarge);
                }
                if (visibility) {
                    dialog.setTitle(title == null ? "" : title);
                    if (msg != null)
                        dialog.setMessage(msg);
                    dialog.show();
                } else if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
