package com.nayan.me.preventsuperbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nayan.me.preventsuperbug.core.Config;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.entity.Complain;
import com.nayan.me.preventsuperbug.entity.User;
import com.nayan.me.preventsuperbug.network.repos.implementes.HttpRepository;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etEmail, etAddress, etContact, etQualification, etHospital, etSpeciality, etRegistration;
    private LinearLayout llDoctor;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        actionbarSetting();
        init();
        bindUserInfo();
    }

    private void actionbarSetting() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Update profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void bindUserInfo() {
        try {
            user = PBSBApplication.getUser();
            if (user != null) {
                etName.setText(user.getFullName() == null ? "" : user.getFullName());
                etEmail.setText(user.getEmail() == null ? "" : user.getEmail());
                etAddress.setText(user.getAddress() == null ? "" : user.getAddress());
                etContact.setText(String.valueOf(user.getContactNumber() == null ? "" : user.getContactNumber()));
                if (user.isDoctor()) {
                    llDoctor.setVisibility(View.VISIBLE);
                    etHospital.setText(user.getHospital() != null ? user.getHospital() : "");
                    etQualification.setText(user.getQualification() != null ? user.getQualification() : "");
                    etRegistration.setText(String.valueOf(user.getRegistrationNumber() != null ? user.getRegistrationNumber() : ""));
                    etSpeciality.setText(user.getSpeciality() == null ? "" : user.getSpeciality());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etContact = findViewById(R.id.etContact);
        etQualification = findViewById(R.id.etQualification);
        etHospital = findViewById(R.id.etHospital);
        etSpeciality = findViewById(R.id.etSpeciality);
        etRegistration = findViewById(R.id.etRegistration);
        llDoctor = findViewById(R.id.llDoctor);
    }

    public void onClick(View view) {
        if (user != null) {
            user.setFullName(etName.getText().toString());
            user.setEmail(etEmail.getText().toString());
            user.setAddress(etAddress.getText().toString());
            user.setContactNumber(etContact.getText().toString());
            if (user.isDoctor()) {
                if (user.doctorRequiredFields().isEmpty()) {
                    user.setHospital(etHospital.getText().toString());
                    user.setRegistrationNumber(etRegistration.getText().toString());
                    user.setSpeciality(etSpeciality.getText().toString());
                    user.setQualification(etQualification.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), user.doctorRequiredFields(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
            updateUserInfo(user);
        }
    }

    private void updateUserInfo(User user) {
        loadingProgressDialog(true, "Profile", "Processing...");
        HttpRepository httpRepository = new HttpRepository(Config.BASE_URL);
        httpRepository.post("api/v1/users", user, User.class, new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                loadingProgressDialog(false, "User", "Processing...");
                Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(EditProfileActivity.this, DashboardActivity.class));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                loadingProgressDialog(false, throwable.getLocalizedMessage(), "Processing...");
            }
        });
    }

    private ProgressDialog dialog;

    protected void loadingProgressDialog(final boolean visibility, @Nullable final String title, @Nullable final String msg) {
        if (dialog == null && !visibility)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(EditProfileActivity.this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
