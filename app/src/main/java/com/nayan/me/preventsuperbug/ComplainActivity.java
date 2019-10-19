package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.Complain;
import com.nayan.me.preventsuperbug.entity.UploadFileResponse;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class ComplainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton submit;
    private EditText providerName;
    private EditText farmacyName;
    private EditText address;
    private EditText details;
    private MaterialCheckBox isPrescribed;
    Uri selectedImage;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        actionbarSetting();
        init();
        bind();
    }

    private void bind() {
        submit.setOnClickListener(this);
    }

    private void init() {
        providerName = findViewById(R.id.providerName);
        farmacyName = findViewById(R.id.farmacyName);
        address = findViewById(R.id.address);
        details = findViewById(R.id.details);
        isPrescribed = findViewById(R.id.isPrescribed);
        submit = findViewById(R.id.submit);
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Complain");
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

    @Override
    public void onClick(View view) {
        uploadImage(selectedImage);
    }

    private void submitComplain(Complain complain) {
        loadingProgressDialog(true, "Complain", "Processing...");
        HttpRepository httpRepository = new HttpRepository(Config.BASE_URL);
        httpRepository.post("api/v1/complains", complain, Complain.class, new Consumer<Complain>() {
            @Override
            public void accept(Complain complain) throws Exception {
                loadingProgressDialog(false, "Complain", "Processing...");
                Toast.makeText(getApplicationContext(), "Your complain is submitted!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ComplainActivity.this, DashboardActivity.class));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                loadingProgressDialog(false, throwable.getLocalizedMessage(), "Processing...");
            }
        });
    }

    private Complain createComplainObj(UploadFileResponse fileResponse) {
        Complain complain = new Complain();
        complain.setProviderName(providerName.getText().toString());
        complain.setFarmacyName(farmacyName.getText().toString());
        complain.setAddress(address.getText().toString());
        complain.setDetails(details.getText().toString());
        complain.setActive(true);
        complain.setPrescribed(isPrescribed.isChecked());
        complain.setUserId(PBSBApplication.getUser().getUserId());
        complain.setPrescriptionImage(fileResponse.getImage());
        submitComplain(complain);
        return complain;
    }

    private void uploadImage(Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        Map<String, File> files = new HashMap<>();
        files.put("file", file);
        HttpRepository uploadImage = new HttpRepository(Config.BASE_URL);
        uploadImage.post("api/v1/uploadFile", new Object(), files, UploadFileResponse.class, new Consumer<UploadFileResponse>() {
            @Override
            public void accept(UploadFileResponse o) throws Exception {
                submitComplain(createComplainObj(o));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                String s = "";
            }
        });
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


    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(ComplainActivity.this);
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

    public void selectImage(View view) {
        selectImage();
    }
}
