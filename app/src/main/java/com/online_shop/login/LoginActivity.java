package com.online_shop.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.online_shop.R;
import com.online_shop.core.TextProcessing;
import com.online_shop.menu.MainMenu;
import com.online_shop.registration.RegisterActivity;

import static java.lang.Thread.sleep;

/**
 * Class for connecting login layout and authorization procedure.
 */

public class LoginActivity extends Activity implements TextProcessing {

    private String LOG_TAG = "Login";
    private TextView answer;
    private String login, password;
    private EditText log, pass;
    SharedPreferences sharedPreferences;
    final String SAVED_ID = "ID";
    int id = 0;
    // -2 -> response hasn't come yet
    // -1 -> connection problems, wrong data
    // 0 -> wrong data
    // any other number -> user id

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //REGISTER
        TextView registerScreen = findViewById(R.id.linkToRegister);
        registerScreen.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        answer = findViewById(R.id.answer);
        log = findViewById(R.id.etEmail);
        pass = findViewById(R.id.etPassword);

        //LOGIN
        Button enterLogin = findViewById(R.id.blogin);
        enterLogin.setOnClickListener(this::sendPOST);
    }

    public void sendPOST(View view) {
        login = log.getText().toString().trim();
        password = pass.getText().toString().trim();

        if (isFieldEmpty(this, log, pass)) return;

        answer.setText(null);
        new SetAnswer();
        answer.setHint(R.string.waiting);
        Thread thread = new Thread(new SetAnswer());
        thread.start();
    }

    public class SetAnswer implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                Authorization auth = new Authorization(login, password);
                auth.start();
                while (Authorization.resp == -2) {
                    try {
                        sleep(3000);
                    } catch (InterruptedException ignored) {
                    }
                }
                id = Authorization.resp;
                if (Authorization.resp == -1 || Authorization.resp == 0) {
                    answer.setText(R.string.login_error);
                    Authorization.resp = -2;
                } else {
                    saveId();
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        }
    }

    void saveId() {
        sharedPreferences = getSharedPreferences("user_setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SAVED_ID, id);
        editor.apply();
        Log.d(LOG_TAG, " id: " + id);
    }
}