package com.online_shop.registration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.online_shop.core.TextProcessing;

import com.online_shop.R;
import com.online_shop.menu.MainMenu;


public class RegisterActivity extends Activity implements TextProcessing {
    private EditText rName, rSurname, rEmail, rPassword, rAddress, rPhoneNumber;
    private TextView answer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rName = findViewById(R.id.etName);
        rSurname = findViewById(R.id.etSurname);
        rEmail = findViewById(R.id.etEmail);
        rPassword = findViewById(R.id.etPassword);
        rAddress = findViewById(R.id.etAddress);
        rPhoneNumber = findViewById(R.id.etMobileNumber);

        //REGISTER
        Button btnRegister = findViewById(R.id.bReg);
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
        String name = getValue(rName);
        String surname = getValue(rSurname);
        String email = getValue(rEmail);
        String password = getValue(rPassword);
        String phone_number = getValue(rPhoneNumber);
        String address = getValue(rAddress);

        if (isFieldEmpty(this, rName, rSurname, rEmail,
                rPassword, rPhoneNumber, rAddress)) return;
        answer = findViewById(R.id.answer);

        Authentication auth = new Authentication();
        auth.execute(name, surname, email, password, phone_number, address);
        processResponse(auth.resp);
    }


}
