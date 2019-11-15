package com.nayan.me.preventsuperbug;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.data.TokenResult;
import com.nayan.me.preventsuperbug.entity.User;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;
import com.nayan.me.preventsuperbug.network.repos.interfaces.common.IPostOnlyRepository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class SignupActivity extends AppCompatActivity {
    private RadioGroup rdRole;
    private LinearLayout doctorLayout;
    private MaterialButton signup;
    private EditText speciality;
    private EditText email;
    private EditText password;
    private EditText fullName;
    private EditText conPassword;
    private EditText qualification;
    private EditText hospital;
    private EditText regNo;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        init();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    private void init() {
        rdRole = findViewById(R.id.rdRole);
        doctorLayout = findViewById(R.id.lldoctor);
        signup = findViewById(R.id.signup);
        hospital = findViewById(R.id.hospital);
        qualification = findViewById(R.id.qualification);
        conPassword = findViewById(R.id.conPassword);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        fullName = findViewById(R.id.fullName);
        speciality = findViewById(R.id.speciality);
        regNo = findViewById(R.id.regNo);
        rdRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.doctor) {
                    doctorLayout.setVisibility(View.VISIBLE);
                } else {
                    doctorLayout.setVisibility(View.GONE);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        User user = new User();
        user.setFullName(fullName.getText().toString());
        user.setPassword(password.getText().toString());
        user.setEmail(email.getText().toString());
        user.setSpeciality(speciality.getText().toString());
        user.setHospital(hospital.getText().toString());
        user.setQualification(qualification.getText().toString());
        user.setRegistrationNumber(regNo.getText().toString());
        user.setRoleId(rdRole.getCheckedRadioButtonId() == R.id.doctor ? 3 : 2);
        if (user.requireFields().isEmpty()) {
            if (rdRole.getCheckedRadioButtonId() == R.id.doctor && !user.doctorRequiredFields().isEmpty()) {
                Toast.makeText(getApplicationContext(), user.doctorRequiredFields(), Toast.LENGTH_LONG).show();
                return;
            }
            loadingProgressDialog(true, "Signup", "Processing...");
            IPostOnlyRepository repository = new HttpRepository(Config.BASE_URL);
            repository.post("api/v1/users", user, User.class, new Consumer<User>() {
                @Override
                public void accept(User user) throws Exception {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    login(user);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    loadingProgressDialog(false, "Signup", "Processing...");
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), user.requireFields(), Toast.LENGTH_LONG).show();
        }
    }


    private void login(User user) {
        JsonObject jb = new JsonObject();
        jb.addProperty("email", user.getEmail());
        jb.addProperty("password", password.getText().toString());
        HttpRepository repository = new HttpRepository(Config.BASE_URL);
        repository.post("login", jb, TokenResult.class, new Consumer<TokenResult>() {
            @Override
            public void accept(TokenResult tokenResult) throws Exception {
                loadingProgressDialog(false, "Logging...", "Please wait");
                PBSBApplication.setToken(tokenResult);
                startActivity(new Intent(SignupActivity.this, DashboardActivity.class));
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
                    dialog = new ProgressDialog(SignupActivity.this);
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
