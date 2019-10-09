package com.example.chattychat.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chattychat.R;
import com.example.chattychat.models.Account;
import com.example.chattychat.repositories.JsonApi;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.chattychat.utils.ConstantsKt.BASE_URL;

public class AccountActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email_field);
        etPassword = findViewById(R.id.et_password);

        Button btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(v -> createAccount());

        Button btnLogIn = findViewById(R.id.btn_log_in);
        btnLogIn.setOnClickListener(v -> login());
    }

    private String getPassword(TextInputEditText etPassword) {

        String s = etPassword.getText().toString().trim();
        if (!s.equals("") && s.length() > 0) {
            Log.d("LOG_IN", s);
            return s;
        } else {
            return null;
        }

    }

    private String getEmail(TextInputEditText etEmail) {
        String s = etEmail.getText().toString().toLowerCase().trim();
        if (!s.equals("") && s.length() > 0) {
            Log.d("LOG_IN", s);
            return s;
        } else {
            return null;
        }
    }

    private void login() {
        email = getEmail(etEmail);
        password = getPassword(etPassword);
        if (email != null && password != null) {
            Account account = new Account(email, password);
            Call<Account> loginCall = jsonApi.login(account);
            loginCall.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Toast.makeText(getApplicationContext(), "Successfully logged in as: " + response.body().getUser(), Toast.LENGTH_SHORT).show();
                            Log.d("LOG_IN", response.body().getUser());
                            Log.d("LOG_IN", response.body().getToken());
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        Log.d("LOG_IN", String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Log.e("ERROR", t.getMessage(), t);
                }
            });
        }
    }

    private void createAccount() {
        email = getEmail(etEmail);
        password = getPassword(etPassword);
        if (email != null && password != null) {
            Account newAccount = new Account(email, password);
            Call<ResponseBody> call = jsonApi.createAccount(newAccount);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Toast.makeText(getApplicationContext(), "Successfully created account: "+email, Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Could not create account!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
