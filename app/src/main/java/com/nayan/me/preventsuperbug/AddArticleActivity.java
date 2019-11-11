package com.nayan.me.preventsuperbug;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.UploadFileResponse;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;
import com.nayan.me.preventsuperbug.network.repos.interfaces.common.IPostOnlyRepository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class AddArticleActivity extends AppCompatActivity {

    private EditText articleTitle;
    private EditText shortDesc;
    private EditText longDesc;
    private MaterialButton submit;
    private ImageButton upload;


    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        articleTitle = findViewById(R.id.articleTitle);
        shortDesc = findViewById(R.id.shortDesc);
        longDesc = findViewById(R.id.longDesc);
        submit = findViewById(R.id.submit);
        upload = findViewById(R.id.upload);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null) {
                    uploadImage(selectedImage);
                } else
                    saveArticle(createArticle());
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private Article createArticle() {
        Article article = new Article();
        article.setArticleTitle(articleTitle.getText().toString());
        article.setShortDesc(shortDesc.getText().toString());
        article.setLongDesc(longDesc.getText().toString());
        article.setUserId(PBSBApplication.getUser().getUserId());
        return article;
    }

    private void saveArticle(Article article) {
        if (article.requiredFields().isEmpty()) {
            IPostOnlyRepository repository = new HttpRepository(Config.BASE_URL);
            repository.post("api/v1/articles", article, Article.class, new Consumer<Article>() {
                @Override
                public void accept(Article article) throws Exception {
                    Toast.makeText(getApplicationContext(), "Article Added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddArticleActivity.this, ArticleActivity.class));
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), article.requiredFields(), Toast.LENGTH_LONG).show();
        }

    }

    private void selectImage() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
        }
    }

    private void uploadImage(Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        Map<String, File> files = new HashMap<>();
        files.put("file", file);
        HttpRepository uploadImage = new HttpRepository(Config.BASE_URL);
        uploadImage.post("api/v1/uploadFile", new Object(), files, UploadFileResponse.class, new Consumer<UploadFileResponse>() {
            @Override
            public void accept(UploadFileResponse o) throws Exception {
                Article article = createArticle();
                article.setThumbImageTitle(o.getImage());
                saveArticle(article);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                String s = "";
            }
        });
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

}
