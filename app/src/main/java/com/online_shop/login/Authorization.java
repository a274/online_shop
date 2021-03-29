package com.online_shop.login;

import android.util.Log;
import com.online_shop.core.App;
import com.online_shop.core.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class for sending login and password data to server for checking whether the user is registered,
 * and the data is correct.
 */

public class Authorization extends Thread {
    private String LOG_TAG = "Authorization";
    public static int resp = -2;
    Account account;


    class Account {
        private String login;
        private String password;

        public Account(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

    public Authorization(String login, String password) {
        account = new Account(login, password);
    }

    @Override
    public void run() {
        UserService userService = App.getInstance().getUserService();

        Call<Integer> userCall = userService.login(account.login, account.password);

        userCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    resp = response.body();
                    Log.v(LOG_TAG, "response " + resp);
                } else {
                    Log.e(LOG_TAG, "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(LOG_TAG, "failure " + t);
            }
        });
    }
}
