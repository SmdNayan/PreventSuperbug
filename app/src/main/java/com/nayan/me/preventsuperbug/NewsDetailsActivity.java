package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.SuperbugNews;

import io.reactivex.annotations.Nullable;

public class NewsDetailsActivity extends AppCompatActivity {

    WebView webView;
    SuperbugNews superbugNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        webView = findViewById(R.id.webView);
        actionbarSetting();
        getData();
    }

    private void getData() {
        superbugNews = new Gson().fromJson(getIntent().getStringExtra("news"), SuperbugNews.class);
        if (superbugNews != null) {

            loadSuperbugWebView();
        }
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("News Details");
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

    private void loadSuperbugWebView() {
        loadingProgressDialog(true, "News", "Loading news");
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                loadingProgressDialog(false, "News", "Loading news");
            }
        };
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(superbugNews.getLink());
    }

    private ProgressDialog dialog;

    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(NewsDetailsActivity.this);
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
