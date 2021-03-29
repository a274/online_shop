package com.online_shop.registration;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.online_shop.core.App;
import com.online_shop.core.TextProcessing;

import com.online_shop.R;
import com.online_shop.core.UserService;
import com.online_shop.menu.MainMenu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity implements TextProcessing {
    private String LOG_TAG = "Register";
    private EditText rName, rSurname, rEmail, rPassword, rAddress, rPhoneNumber;
    private String name, surname, email, password, phone_number, address;
    private TextView answer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        rName = findViewById(R.id.reg_name);
        rSurname = findViewById(R.id.reg_surname);
        rEmail = findViewById(R.id.reg_email);
        rPassword = findViewById(R.id.reg_password);
        rAddress = findViewById(R.id.reg_address);
        rPhoneNumber = findViewById(R.id.reg_mobilenumber);

        // LOGIN
        TextView loginScreen = findViewById(R.id.link_to_login);
        loginScreen.setOnClickListener(v -> finish());

        //REGISTER
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this::sendPOST);
    }

    // обработка ответов с сервера о регистрации
    public void processResponse(int resp){
        if (resp == -2)  {
            answer.setHint(R.string.been_registered);
            //  Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            //  startActivity(i);
        }
        else if (resp == -1) answer.setHint(R.string.reg_error);
        else {
            answer.setText(R.string.register_success);
            Intent i = new Intent(RegisterActivity.this, MainMenu.class);
            startActivity(i);
        }
    }

    //обработка нажатия конпки
    public void sendPOST(View view) {
        name = getValue(rName);
        surname = getValue(rSurname);
        email = getValue(rEmail);
        password = getValue(rPassword);
        phone_number = getValue(rPhoneNumber);
        address = getValue(rAddress);

        if (isFieldEmpty(this, rName, rSurname, rEmail,
                rPassword, rPhoneNumber, rAddress)) return;
        answer = findViewById(R.id.answer);

        new MyAsyncTask().execute(name, surname, email, password, phone_number, address);
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String[] par = params;
            UserService userService = App.getInstance().getUserService();

            Call<Integer> userCall = userService
                    .register(par);

            userCall.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        processResponse(response.body());
                        Log.v(LOG_TAG, "response " + response.body());

                    } else {
                        answer.setHint(R.string.reg_error);
                        Log.e(LOG_TAG,"response code " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Log.e(LOG_TAG,"failure " + t);
                }
            });
            return null;
        }
    }
}
