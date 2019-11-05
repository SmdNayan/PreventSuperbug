package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nayan.me.preventsuperbug.entity.Article;

public class ArticleDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        init();
        actionbarSetting();
        getData();
        bindData();
    }

    private void bindData() {
        if (article != null) {
            title.setText(article.getActive());
            description.setText(article.getLongDesc());
        }
    }

    private void getData() {
        article = new Gson().fromJson(getIntent().getStringExtra("article"), Article.class);
    }

    private void init() {
        title = findViewById(R.id.atitle);
        description = findViewById(R.id.description);
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("ARTICLE DETAILS");
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
