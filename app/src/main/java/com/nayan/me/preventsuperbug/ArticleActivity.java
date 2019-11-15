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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.nayan.me.preventsuperbug.adapter.ArticleAdapter;
import com.nayan.me.preventsuperbug.adapter.MedicineAdapter;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.Medicine;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView rcvArticle;
    private MaterialButton addArticle;
    private TextView tvArticle;
    private TextView tvAllArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        init();
        actionbarSetting();
        getArticles();
    }

    private void getArticles() {
        loadingProgressDialog(true, "Article", "Loading...");
        HttpRepository getMedicines = new HttpRepository(Config.BASE_URL);
        getMedicines.get("api/v1/articles", Article[].class, new Consumer<Article[]>() {
            @Override
            public void accept(Article[] articles) throws Exception {
                if (articles != null)
                    bindData(Arrays.asList(articles));
                loadingProgressDialog(false, "Article", "Loading...");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                HttpException exception = (HttpException) throwable;
                if (exception.code() == 403) {
                    Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_LONG).show();
                }
                loadingProgressDialog(false, "Article", "Loading...");
            }
        });
    }

    private void bindData(List<Article> articleList) {
        ArticleAdapter adapter = new ArticleAdapter(this);
        rcvArticle.setAdapter(adapter);
        rcvArticle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvArticle.setItemAnimator(new DefaultItemAnimator());
        rcvArticle.setHasFixedSize(true);
        adapter.setArticles(articleList);
    }

    private void init() {
        rcvArticle = findViewById(R.id.rcvArticle);
        addArticle = findViewById(R.id.addArticle);
        tvArticle = findViewById(R.id.tvArticle);
        tvAllArticle = findViewById(R.id.tvAllArticle);
        addArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PBSBApplication.isLoggedIn())
                    startActivity(new Intent(ArticleActivity.this, AddArticleActivity.class));
            }
        });
        tvArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PBSBApplication.isLoggedIn()) {
                    loadMyArticles(PBSBApplication.getUser().getUserId());
                }
            }
        });
        tvAllArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getArticles();
            }
        });
    }

    private void loadMyArticles(int userId) {
        loadingProgressDialog(true, "Article", "Loading...");
        HttpRepository getMedicines = new HttpRepository(Config.BASE_URL);
        getMedicines.get("api/v1/articles-by-user/" + userId, Article[].class, new Consumer<Article[]>() {
            @Override
            public void accept(Article[] articles) throws Exception {
                if (articles != null)
                    bindData(Arrays.asList(articles));
                loadingProgressDialog(false, "Article", "Loading your articles...");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                HttpException exception = (HttpException) throwable;
                if (exception.code() == 403) {
                    Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "You Have No Article!", Toast.LENGTH_LONG).show();
                }
                loadingProgressDialog(false, "Article", "Loading...");
            }
        });
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("ARTICLE");
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
                    dialog = new ProgressDialog(ArticleActivity.this);
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
