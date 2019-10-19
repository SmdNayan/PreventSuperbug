package com.nayan.me.preventsuperbug;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.data.TokenResult;
import com.nayan.me.preventsuperbug.network.http.CiHttpClient;
import com.nayan.me.preventsuperbug.network.http.HttpService;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    EditText usernameEditText;
    EditText passwordEditText;
    MaterialButton loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        loadingProgressDialog(true, "Logging...", "Please wait");
        JsonObject jb = new JsonObject();
        jb.addProperty("email", usernameEditText.getText().toString());
        jb.addProperty("password", passwordEditText.getText().toString());
        HttpRepository repository = new HttpRepository(Config.BASE_URL);
        repository.post("login", jb, TokenResult.class, new Consumer<TokenResult>() {
            @Override
            public void accept(TokenResult tokenResult) throws Exception {
                loadingProgressDialog(false, "Logging...", "Please wait");
                PBSBApplication.setToken(tokenResult);
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                loadingProgressDialog(false, "Logging...", "Please wait");
                Toast.makeText(getApplicationContext(), "Failed to logged in!", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(LoginActivity.this);
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
