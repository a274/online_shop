package com.online_shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.online_shop.login.LoginActivity;
import com.online_shop.menu.MainMenu;

public class StartIcon extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    final String SAVED_ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ---------------- TO DO LAYOUT
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("user_setting", MODE_PRIVATE);
        int id = sharedPreferences.getInt(SAVED_ID, 0);
        synchronized (this) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent;
            if (id > 0) {
                intent = new Intent(StartIcon.this, MainMenu.class);
            } else {
                intent = new Intent(StartIcon.this, LoginActivity.class);
            }
            startActivity(intent);
        }
    }
}
